package com.github.mstraub.devbrc.java7.trywithresources;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class implements {@link Closeable} and always throws an exception in the
 * constructor as well as in {@link #close()}. This class is used to demonstrate
 * suppression of exceptions.
 * 
 * @author mstraub
 */
public class MyExceptionsEverywhereCloseable implements Closeable {

	private int a;

	public MyExceptionsEverywhereCloseable(int a) throws IOException {
		System.err.println("MyExceptionsEverywhereCloseable is born (with an Exception): " + a);
		this.a = a;
		throw new IOException("IOE in " + a);
	}

	public int getA() {
		return a;
	}

	/**
	 * My own always-failing close method, throws a FileNotFoundException
	 */
	@Override
	public void close() throws FileNotFoundException {
		System.err.println("MyExceptionsEverywhereCloseable is closed (but throws an Exception): " + a);
		int oldA = a;
		a = 0;
		throw new FileNotFoundException(oldA + " is not a valid file.");
	}

}
