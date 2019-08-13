package com.github.mstraub.common;

public class Wurbelwurf {

	public enum Horst {BEINE, ARME, KÖRPER}
	
	public static void main(String[] args) {
		String teil = "BEINE";
		System.out.println(teil.equals(Horst.BEINE.toString()));
	}
	
}
