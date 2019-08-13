package com.github.mstraub.gis;


public class Slope {
	
	public static double getSlope(double h, double d) {
		return 100 * Math.tan(Math.asin(h/d));
	}
	
	public static double getSlopeEasy(double h, double d) {
		return 100 * h/d;
	}
	
	public static void main(String[] args) {
		double d = 50;
		for(double h=0; h<35; h++)
			System.out.println(String.format("%.2f - %.2f", getSlope(h/2, d), getSlopeEasy(h/2, d)));
	}
	

}
