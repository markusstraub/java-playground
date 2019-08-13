package com.github.mstraub.common;

public class ExtendedDefaultClass extends DefaultClass {

	public ExtendedDefaultClass() {
//		value = 34;
	}
	
	public static void main(String[] args) {
		ExtendedDefaultClass edc = new ExtendedDefaultClass();
		System.out.println(edc.getValue());
		System.out.println(edc.getCollection().getClass());
	}
	
}
