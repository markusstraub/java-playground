package com.github.mstraub.basejava;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateStuff {
	
	public static void main(String[] args) {
		System.out.println(TimeZone.getTimeZone("GMT+1:00"));
		GregorianCalendar cet = new GregorianCalendar(TimeZone.getTimeZone("GMT+1:00"));
		cet.set(2013, Calendar.MARCH, 31, 2, 0, 0);
		System.out.println(cet.getTime());
		
		System.out.println(TimeZone.getTimeZone("GMT+2:00"));
		GregorianCalendar cest = new GregorianCalendar(TimeZone.getTimeZone("GMT+2:00"));
		cest.set(2013, Calendar.MARCH, 31, 2, 0, 0);
		System.out.println(cest.getTime());

		long diff = cest.getTimeInMillis() - cet.getTimeInMillis();
		System.out.println("millis diff: " + diff);
	}
	
}
