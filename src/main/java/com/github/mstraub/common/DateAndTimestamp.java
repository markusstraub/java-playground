package com.github.mstraub.common;

import java.sql.Timestamp;
import java.util.Date;

public class DateAndTimestamp {

	public static void main(String[] args) {
		Date d = new Date();
		Timestamp ts = new Timestamp(d.getTime());
		
		System.out.println(d.equals(ts));
		System.out.println(d.after(ts));
		System.out.println(d.before(ts));
	}
	
}
