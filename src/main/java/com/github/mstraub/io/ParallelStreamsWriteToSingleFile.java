package com.github.mstraub.io;

import java.util.stream.IntStream;

public class ParallelStreamsWriteToSingleFile {

	private static final int MIN = 19;
	private static final int MAX = 153;
	private static final int STEP = 10;

	public static void main(String[] args) {
		int max = (MAX - MIN)/STEP;
		
		IntStream.rangeClosed(0, max).boxed().forEach(ParallelStreamsWriteToSingleFile::doSomeWorkStepsize);
//		IntStream.iterate(min, i -> i + step).limit(10).boxed().parallel().forEach(ParallelStreamsWriteToSingleFile::doSomeWork);
	}
	
	private static synchronized void doSomeWork(int i) {
		System.out.println(String.format("%d-%d processed by thread %d", i, i+STEP-1, Thread.currentThread().getId()));
	}
	
	private static synchronized void doSomeWorkStepsize(int i) {
		System.out.println(String.format("%d-%d processed by thread %d", MIN + i*STEP, MIN + i*STEP+STEP-1, Thread.currentThread().getId()));
	}

}
