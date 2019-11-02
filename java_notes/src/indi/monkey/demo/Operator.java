package indi.monkey.demo;
/**
 * 运算符
 * @author tangjf
 *
 */
public class Operator {
	/**
	 *  + - *
	 **/
	 public static void main(String[] args) {
		int a = 2*2;
		int b = 2/1;
		int c = 2 +1;
		int d = 2 -1;
		int e = 2 <<1;
		int f = 2 >>1; // 00000010 -> 00000100
		int g = 2^3; // 00000010|00000011 
		int h = 5|3; // 00000100|00000011
		System.out.println(Integer.toBinaryString(2));
		int i = 6&2; // 00000110&00000010
		System.out.println("a:"+a);
		System.out.println("b:"+b);
		System.out.println("c:"+c);
		System.out.println("d:"+d);
		System.out.println("e:"+e);
		System.out.println("f:"+f);
		System.out.println("g:"+g);
		System.out.println("h:"+h);
		System.out.println("i:"+i);
	}
}
