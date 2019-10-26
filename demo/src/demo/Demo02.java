package demo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;

public class Demo02 {
	static BlockingQueue<Integer> queue = new LinkedBlockingDeque<Integer>();
	
	static CountDownLatch latch = new CountDownLatch(10);
	
	static class MyThread extends Thread{
		private int i;
		public MyThread(int i) {
			this.i = i;
		}
		@Override
		public void run() {
			long time = (long) (Math.random()*1000);
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			queue.add(i);
			latch.countDown();
		}
	}
	
	static class ReleaseThread extends Thread {
		@Override
		public void run() {
			try {
				latch.await();
			} catch (Exception e) {
			}
			System.out.println("release");
		}
	}
	
	public static void main(String[] args) {
		for(int index = 0;index < 10;index++) {
			int i = (int) (Math.random() * 10);
			new MyThread(i).start();
		}
	//	ReleaseThread releaseThread = new ReleaseThread();
	//  releaseThread.start();
		try {
			latch.await();
		} catch (Exception e) {
		}
		System.out.println(queue);
	}
}
