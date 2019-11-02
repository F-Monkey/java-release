package indi.monkey.demo.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LoaderClass extends ClassLoader{
	private static byte[] getBytes(String classFile) throws IOException {
		File f = new File(classFile);
		FileInputStream fis = new FileInputStream(f);
		byte[] bytes = new byte[1024];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len = 0;
		while((len = fis.read(bytes))>0) {
			baos.write(bytes,0,len);
		}
		return baos.toByteArray();
	}
	
	@SuppressWarnings("deprecation")
	private Object byteArr2Obj(byte[] bytes) throws InstantiationException, IllegalAccessException {
		//Class<?> defineClass = this.defineClass(bytes, 0, bytes.length);
		Class<?> defineClass = this.defineClass(null, bytes, 0, bytes.length);
		// Object
		return defineClass.newInstance();
	}
	
	public static void main(String[] args) throws Exception {
		LoaderClass loaderClass = new LoaderClass();
		byte[] bytes = getBytes("src/indi/monkey/demo/classLoader/A.class");
		Object obj = loaderClass.byteArr2Obj(bytes);
		Method method = obj.getClass().getMethod("print");
		method.invoke(obj);
		Class<? extends Object> clazz = obj.getClass();
		Field field = obj.getClass().getDeclaredField("a");
		field.setAccessible(true);
		Object object = field.get(obj);
		System.out.println(object);
		
	}
}
