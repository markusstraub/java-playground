package com.github.mstraub.devbrc.java7.trywithresources;

import java.io.Closeable;
import java.io.IOException;

/**
 * This is a well-behaving class that implements {@link Closeable} and does not
 * even throw any Exception on {@link #close()}.
 * <p>
 * <b>So what is this all about?</b><br/>
 * {@link AutoCloseable}: close() throws Exception, strongly suggested to be
 * idempotent<br>
 * {@link Closeable}: close() throws IOException, required to be idempotent<br>
 * <p>
 * idempodent = required to have no effect if called more than once
 * <p>
 * If it would cause problems for an exception to be suppressed, the
 * close() method should not throw it.
 * 
 * @author markus
 */
public class MyCloseable implements Closeable {

	private int a;

	public MyCloseable(int a) {
		this.a = a;
		System.err.println("MyCloseable is born: " + a);
	}

	public int getA() {
		return a;
	}

	/**
	 * My own close method, does not even throw an Exception!
	 * <p>
	 * We could throw any exception derived from {@link IOException} or
	 * {@link IOException} itself though.
	 */
	@Override
	public void close() {
		System.err.println("MyCloseable is closed: " + a);
		a = 0;
	}

}
