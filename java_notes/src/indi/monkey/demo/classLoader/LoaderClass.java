package indi.monkey.demo.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LoaderClass extends ClassLoader{
	private static byte[] getBytes(String classFile) throws IOException {
		File f = new File(classFile);
		System.out.println(f.getAbsolutePath());
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
		Class<?> defineClass = this.defineClass(bytes, 0, bytes.length);
		System.out.println(defineClass.getName());
		return defineClass.newInstance();
	}
	
	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {
		LoaderClass loaderClass = new LoaderClass();
		byte[] bytes = getBytes("src/indi/monkey/demo/classLoader/A.class");
		Object obj = loaderClass.byteArr2Obj(bytes);
		System.out.println(obj.getClass());
		
	}
}
