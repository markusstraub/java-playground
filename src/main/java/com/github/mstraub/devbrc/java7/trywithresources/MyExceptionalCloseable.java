package com.github.mstraub.devbrc.java7.trywithresources;

import java.io.Closeable;
import java.io.FileNotFoundException;

/**
 * This class implements {@link Closeable}, and refines the exception that may
 * be thrown in {@link #close()}.
 * <p>
 * Actually, it always throws a {@link FileNotFoundException} :)
 * 
 * @author mstraub
 * 
 */
public class MyExceptionalCloseable implements Closeable {

	private int a;

	public MyExceptionalCloseable(int a) {
		System.err.println("MyExceptionalClosable is born: " + a);
		this.a = a;
	}

	public int getA() {
		return a;
	}

	/**
	 * My own always-failing close() method, sets a to 0 and then throws a
	 * {@link FileNotFoundException}.
	 */
	@Override
	public void close() throws FileNotFoundException {
		System.err.println("MyExceptionalClosable is closed (but throws an Exception): " + a);
		int oldA = a;
		a = 0;
		throw new FileNotFoundException(oldA + " is not a valid file.");
	}

}
