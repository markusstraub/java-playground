package com.github.mstraub.performance;

public abstract class TestCase {

	/**
	 * All preparation, initialization, parsing of data goes here.
	 */
	public abstract void prepareTest();
	
	/**
	 * Calculations or juggling with parsed data goes here.
	 * @param iterations
	 * 					How often the same operation should be repeated (so that we get a good
	 * 					medium)
	 */
	public abstract void conductTest(int iterations);
	
	public abstract void conductTest2(int iterations);
	
	public abstract void conductTest3(int iterations);
	
	public abstract void conductTest4(int iterations);
}
