package com.github.mstraub.basejava;

import java.text.DecimalFormat;

public class DoublePrinting {
	public static void main(String[] args) {
		double nan = Double.NaN;
		double nr = 12.3456789;
		DecimalFormat df = new DecimalFormat("#.##");
		
		System.out.println(nan + " " + nr);
		System.out.println(String.format("%.2f %.2f", nan, nr));
		System.out.println(df.format(nan) + " " + df.format(nr));
		
		double d = 1d/0d;
		System.out.println("1/0: " + d);
		d *= 0;
		System.out.println("(1/0) * 0: " + d);
	}
}
