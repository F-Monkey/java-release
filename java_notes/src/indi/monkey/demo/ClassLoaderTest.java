package indi.monkey.demo;

import java.util.HashMap;
import java.util.Map;

public class ClassLoaderTest {
	public static void main(String[] args) {
		String a = new String("111");
		Map b = new HashMap();
		ClassLoader StringLoader = a.getClass().getClassLoader();
		ClassLoader mapLoader = b.getClass().getClassLoader();
		System.out.println(StringLoader);
		System.out.println(mapLoader);
		System.out.println(ClassLoaderTest.class.getClassLoader());
		
	
		
	}
}
