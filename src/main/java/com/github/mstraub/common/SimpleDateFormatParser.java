package com.github.mstraub.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatParser {
	
	static SimpleDateFormat sdfMillis = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void main(String[] args) throws ParseException {
		String s1 = "2009-10-21 07:36:01.123456789";
		String s2 = "2009-10-21 07:36:01.12";
		String s3 = "2009-10-21 07:36:01";
		parseDate(s1);
		parseDate(s2);
		parseDate(s3);
	}
	
	
	private static Date parseDate(String s) {
		Date d = null;
		if (s.contains(".")) {
			int cut = s.lastIndexOf(".") + 4;
			if(cut > s.length())
				cut = s.length();
			s = s.substring(0, cut);
			try {
				d = sdfMillis.parse(s);
				System.out.println(s + " = " + d + " (" + d.getTime() + ")");
			} catch (ParseException pe) {
				pe.printStackTrace();
			}
		} else {
			try {
				d = sdf.parse(s);
				System.out.println("____fallback: " + s + " = " + d + " (" + d.getTime() + ")");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return d;
	}
}
//2009-10-21 07:36:01.991456 = 1256104352456
//2009-10-21 07:36:01.991    = 1256103361991