package com.github.mstraub.exceptions;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class TryFinally {

	public static void main(String[] args) {
		List<TestSubject> subjects = ImmutableList.of(new UncaughtExceptionInTryBlock(),
				new CaughtExceptionInTryBlock(), new ExceptionInFinallyBlockMaskingPreviousException());

		for (TestSubject subject : subjects) {
			System.err.println("results for " + subject.getClass().getSimpleName() + ":");
			try {
				subject.doSomething();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.err.println("\n\n");
		}
	}

	private static interface TestSubject {
		void doSomething();
	}

	private static class UncaughtExceptionInTryBlock implements TestSubject {
		@Override
		public void doSomething() {
			try {
				throw new RuntimeException("exception in try block");
			} finally {
			}
		}
	}

	private static class CaughtExceptionInTryBlock implements TestSubject {
		@Override
		public void doSomething() {
			try {
				throw new RuntimeException("exception in try block");
			} catch (Exception e) {
				throw new RuntimeException("wrapping the previous exception in another one", e);
			} finally {
			}
		}
	}

	private static class ExceptionInFinallyBlockMaskingPreviousException implements TestSubject {
		@Override
		public void doSomething() {
			try {
				throw new RuntimeException("exception in try block");
			} finally {
				throw new RuntimeException("exception in finally block");
			}
		}
	}

}
