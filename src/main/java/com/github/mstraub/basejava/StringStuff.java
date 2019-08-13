package com.github.mstraub.basejava;

public class StringStuff {
	public static void main(String[] args) {
		String a = null;
		String b = null;
		System.out.println(a);
		System.out.println(a + " " + b);
		
		System.out.println(String.format("%s,%b,%B", false, false,false));
	}
}
