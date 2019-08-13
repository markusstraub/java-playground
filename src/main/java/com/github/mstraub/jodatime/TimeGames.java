package com.github.mstraub.jodatime;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

public class TimeGames {
	
	public static void timeFromString() {
		// when using constructor: resulting DateTime objects will use local time
		System.out.println(new DateTime("2013-01-01T00:00"));
		System.out.println(new DateTime("2013-01-01T00:00+00"));
		System.out.println(new DateTime("2013-01-01T00:00+0000"));
		System.out.println(new DateTime("2013-01-01T00:00+00:00"));
		System.out.println(new DateTime("2013-01-01T00:00-10:30"));
		
//		System.out.println(new DateTime("2013-01-01 00:00-10:30"));
		
//		System.out.println(ISODateTimeFormat.dateTimeNoMillis().parseDateTime("2013-01-01T00:00"));
		
		//http://joda-time.sourceforge.net/api-release/org/joda/time/format/ISODateTimeFormat.html#dateTimeParser%28%29
		// time zone of date String is used.
		DateTime time;
		
		time = new DateTime(DateTime.parse("2012-08-16T07:22:05Z"));
		System.out.println(time + " | " + time.toDate());
		
		time = new DateTime(DateTime.parse("2012-08-16T07:22:05"));
		System.out.println(time + " | " + time.toDate());
		
		time = new DateTime(DateTime.parse("2012-08-16T07:22:05+0200"));
		System.out.println(time + " | " + time.toDate());
	}
	
	public static void millis() {
		DateTime epochFail = new DateTime("1970-01-01T00:00");
		System.out.println(epochFail + " - not epoch if the system-timezone is not UTC");
		System.out.println(epochFail.getMillis());
		
		DateTime epoch = new DateTime("1970-01-01T00:00+00:00");
		System.out.println(epoch + " - epoch on all systems");
		System.out.println(epoch.getMillis());
	}
	
	public static void main(String[] args) {
		timeFromString();
//		millis();
		
		
//		DateTime firstDay = new DateTime("2013-12-09T00:00");
//		System.out.println(firstDay);
//		System.out.println(firstDay.dayOfMonth().get());
//	    System.out.println(firstDay.dayOfMonth().roundFloorCopy());
//	    
//	    DateTime lastDay = new DateTime("2013-12-10T23:59");
//	    System.out.println(lastDay);
//	    
//	    firstDay.compareTo(lastDay);
//	    
//	    Days d = Days.daysBetween(firstDay, lastDay);
//	    System.out.println(d.getDays());
	    
	}
}
