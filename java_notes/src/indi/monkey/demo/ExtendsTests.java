package indi.monkey.demo;

import java.lang.reflect.Field;

import indi.monkey.demo.ExtendsTests.C;

public class ExtendsTests {
	static class A{
		protected String paramA;
		
		public A(){
			System.out.println("A init");
		}
		
		public void print() {
			System.out.println("print");
		}
	}
	
	static class B extends A{
		private String paramB;
		protected String paramB1;
		public String paramB2;
	}
	
	static class C extends B{
		
	}
	public static void main(String[] args) throws Exception{
		A a = new A();
		a.print();
		C c = new C();
		System.out.println("B->C:"+B.class.isAssignableFrom(C.class));//
		System.out.println("B->A:"+B.class.isAssignableFrom(A.class));//
		System.out.println("C->A:"+A.class.isAssignableFrom(C.class));//
		System.out.println(a instanceof B);
		System.out.println(c instanceof B);
		Class<? super C> superclass = C.class.getSuperclass();
		System.out.println(superclass);
		Class<?>[] interfaces = B.class.getInterfaces();
		System.out.println(interfaces.length);
		Field[] declaredFields = B.class.getDeclaredFields();
		System.out.println(declaredFields);
		Field[] fields = B.class.getFields();
		/**
		 * A ... interface method eat() A class
		 * B -> CLASS eat() -> A  
		 * invoke
		 */
		
		
	}
}
