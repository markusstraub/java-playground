package com.github.mstraub.basejava;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Exceptions {
	public static void main(String[] args) {

		//new java.util.Random()
		
		// no exception.. 
		Calendar c = new GregorianCalendar(2000, Calendar.JANUARY, 32);
		System.out.println(c.getTime());
		
		// IllegalArgumentException
		ArrayList<Object> list = new ArrayList<Object>(-1);
		
	}
}
