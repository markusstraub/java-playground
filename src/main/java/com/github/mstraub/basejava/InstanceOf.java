package com.github.mstraub.basejava;

public class InstanceOf {
	public static void main(StringStuff[] args) {
		Double d = 5d;
		Double e = null;
		System.out.println(d instanceof Double);
		System.out.println(e instanceof Double);
		System.out.println(getNull() instanceof Double);
		System.out.println(null instanceof Double);
	}
	
	public static Object getNull() {
		return null;
	}
}
