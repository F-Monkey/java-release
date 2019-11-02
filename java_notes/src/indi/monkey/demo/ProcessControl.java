package indi.monkey.demo;

public class ProcessControl {
	public static void main(String[] args) {
		lable:for(;;) {
			for(int i=0;i<=10;i++) {
				if(i == 2) {
					break lable;
				}
			}
		}
		System.out.println("end");
	}
}
