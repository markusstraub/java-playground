package com.github.mstraub.jodatime;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;


public class TimeZoneTest {
	
	public static void main(String[] args) {
		
		DateTimeZone vienna = DateTimeZone.forID("Europe/Vienna");
		DateTimeZone winter = DateTimeZone.forOffsetHours(1);
		DateTimeZone summer = DateTimeZone.forOffsetHours(2);
		
		System.out.println(vienna);
		System.out.println(DateTimeZone.getDefault());
	
		// winter time -> summer time
		print(new DateTime(2014, 03, 30, 01, 59, 59, vienna));
		print(new DateTime(2014, 03, 30, 03, 00, 00, vienna));
		
		// summer time -> winter time
		print(new DateTime(2014, 10, 26, 02, 59, 59, summer));
		print(new DateTime(2014, 10, 26, 02, 00, 00, winter));
	}
	
	private static void print(DateTime time) {
		System.out.println(time.getMillis() + ": " + time);
	}

}
