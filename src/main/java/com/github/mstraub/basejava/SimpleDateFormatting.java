package com.github.mstraub.basejava;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SimpleDateFormatting {
	public static void main(String[] args) throws ParseException {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
//		System.out.println(sdf.parse("2012-01-01T08:00+0000"));
		System.out.println(sdf.parse("2012-07-31T08:00:00+0200"));
		
		
		sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mmX");
		System.out.println(sdf.parse("2012-01-01T08:00Z"));
	}
}
