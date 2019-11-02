package indi.monkey.demo;

public class InitDemo {
	static {
		System.out.println("static");
	}
	
	public InitDemo() {
		System.out.println("construct");
	}
}
