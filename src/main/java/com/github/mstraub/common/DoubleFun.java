package com.github.mstraub.common;

public class DoubleFun {

	public static void main(String[] args) {
		System.out.println(Double.MAX_VALUE);
		System.out.println(Double.MAX_VALUE * 2);
		System.out.println(Double.POSITIVE_INFINITY);
		
		System.out.println(Double.MAX_VALUE < Double.POSITIVE_INFINITY);
		System.out.println(Double.MAX_VALUE * 2 < Double.POSITIVE_INFINITY);
		System.out.println(Double.POSITIVE_INFINITY * 2 < Double.POSITIVE_INFINITY);
	}
}
