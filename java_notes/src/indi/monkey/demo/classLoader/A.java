package indi.monkey.demo.classLoader;

public class A {
	
	static {
		System.out.println("static");
	}
	
	Integer a = new Integer(10);
	
	public A() {
		System.out.println("A");
	}
	
	public void print() {
		System.out.println("aaa");
	}	
}
