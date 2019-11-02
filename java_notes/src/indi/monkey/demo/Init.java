package indi.monkey.demo;
/**
 * 初始化
 * @author tangjf
 *
 */

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

import sun.misc.Unsafe;

public class Init {
	
	static Unsafe getUnsafe() throws Exception {
		Class<Unsafe> unsafeClass = Unsafe.class;
		Field declaredField = unsafeClass.getDeclaredField("theUnsafe");
		declaredField.setAccessible(true);
		return (Unsafe) declaredField.get(null);
	}
	public static void main(String[] args) throws Exception{
//		InitDemo initDeme = new InitDemo();
//		InitDemo initDemo = InitDemo.class.newInstance();
		Unsafe unsafe = getUnsafe();// unsafe
		// 它跳过了构造方法
		InitDemo initDemo = (InitDemo) unsafe.allocateInstance(InitDemo.class);
	}
}
