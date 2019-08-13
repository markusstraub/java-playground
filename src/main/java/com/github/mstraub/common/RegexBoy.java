package com.github.mstraub.common;

import java.util.regex.Pattern;

public class RegexBoy {
	public static void main(String[] args) {
		String str = "blabla\nblablabla\nblaaaarg\n\rblasdfasdfasdf";
		for (String s : str.split("\\n\\r")) {
			System.out.println("__" + s + "__");
		}
		
		String[] testStrings = {"0", "0_abc_213", "_0123", "abc"};
		for(String s : testStrings)
		if(s.matches("[a-zA-Z_].*"))
			System.out.print("y");
		else
			System.out.print("n");
		
		// expected
		System.out.println();
		System.out.println("nnyy");
			
		regexPatterns();
	}
	
	public static void regexPatterns() {
	    String nightBusVienna = "N\\d+";
	    printMatch(nightBusVienna, "N1");
        printMatch(nightBusVienna, "N61");
        printMatch(nightBusVienna, "U1");
        
        String busVienna = "\\d+[A|B]";
        printMatch(busVienna, "1A");
        printMatch(busVienna, "40A");
        printMatch(busVienna, "68B");
        printMatch(busVienna, "104");
        printMatch(busVienna, "40");
	}
	
	private static void printMatch(String regex, String s) {
	    Pattern p = Pattern.compile(regex);
	    System.out.println(s + (p.matcher(s).matches() ? " matches " : " does NOT match ") + regex);
	}
}
