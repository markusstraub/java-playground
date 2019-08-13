package com.github.mstraub.test.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExceptionLogging {
	protected static Logger log = Logger.getLogger(ExceptionLogging.class
			.getName());

	public static void main(String[] args) {

		try {

			String bla = null;
			System.out.println(bla.charAt(0));

		} catch (Exception e) {
			log.log(Level.SEVERE, "", e);
			e.printStackTrace();
		}

	}

}
