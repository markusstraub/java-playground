package com.github.mstraub.common;

public class ArrayFun {
	public static void main(String[] args) {
		giveMeAnArray(new int[]{1, 2});
		giveMeAnArray(null);
	}

	private static void giveMeAnArray(int[] arr) {
		if(arr != null)
			System.out.println(arr.length);
	}
}
