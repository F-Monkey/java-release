package indi.monkey.demo;

public class ExtendsTests {
	static class A{
		
		public A(){
			System.out.println("A init");
		}
		
		public void print() {
			System.out.println("print");
		}
	}
	
	static class B extends A{
	}
	public static void main(String[] args) {
		A a = new B();
		a.print();
	}
}
