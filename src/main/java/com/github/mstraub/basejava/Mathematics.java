package com.github.mstraub.basejava;


public class Mathematics {
	public static void main(String[] args) {
		System.out.println(3/4*2);
		
		int a = 600_000;
		System.out.println(a * 3_600_000);
		long l = a * 3_600_000;
		System.out.println(l);
		
		// correct results with casting :)
		System.out.println((long)a * 3_600_000);
		System.out.println(a * 3_600_000L);
	}
}
