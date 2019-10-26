package demo;

public class Demo01 {
	public static void main(String[] args) {
		Object obj = new Object() {
			@Override
			protected void finalize() throws Throwable {
				System.out.println("I am going to die~~");
			}
		};
		obj = null;
		System.out.println("start gc");
		System.gc();
		System.out.println("end gc");
	}
}
