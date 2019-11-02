package indi.monkey.demo;

import java.util.ArrayList;

/**
 * 面向对象与面向过程
 * @author tangjf
 *
 */
public class Demo01 {
	public static int[] add(int[] arr,int i) {
		if(arr == null) {
			arr = new int[0];
		}
		int[] arr_temp = new int[arr.length +1];
		for(int index = 0;index<arr.length;index ++) {
			arr_temp[index] = arr[index];
		}
		arr_temp[arr_temp.length -1] = i;
		return arr_temp;
	}
	public static void main(String[] args) {
		int[] arr = null;
		for(int i = 1; i< 9;i++) {
			arr = add(arr,i);
		}
		for(int i = 0;i<arr.length;i++) {
			System.out.print(arr[i]+",");
		}
		ArrayList<Integer> list =new ArrayList<Integer>();
		list.add(1);
	}
}
