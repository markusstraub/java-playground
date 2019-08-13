package com.github.mstraub.devbrc.java7.trywithresources;

import java.io.FileNotFoundException;
import java.io.IOException;


public class TryWithResourcesInTheory {

	/**
	 * Simple example for try-with-resources. No exceptions are caught, and
	 * close() is called automatically.
	 */
	private static void simple() {
		try (MyCloseable mc = new MyCloseable(1)) {
			System.err.println(mc.getA());
		}
	}
	
//	private static void variableCanNotDefinedOutside() {
//		MyCloseable mc;
//		try (mc = new MyCloseable(1)) {
//			System.err.println(mc.getA());
//		}
//	}

	/**
	 * Note: the instance created within the try-with-resources-statement is (of
	 * course) not available in the catch or finally block.
	 */
//	private static void variableNotAvailableInCatchOrFinally() {
//		try (MyCloseable mc = new MyCloseable(1)) {
//			System.err.println(mc.getA());
//		} catch (Exception e) {
//			System.err.println(mc.getA());
//		} finally {
//			System.err.println(mc.getA());
//		}
//	}

	
	/**
	 * Resources are closed in reverse order of declaration
	 */
	private static void multipleResources() {
		try (
				MyCloseable mc = new MyCloseable(1);
				MyCloseable mc2 = new MyCloseable(2);
				MyCloseable mc3 = new MyCloseable(3)) {
			System.err.println(mc.getA());
			System.err.println(mc2.getA());
			System.err.println(mc3.getA());
		}
	}

	/**
	 * try-with-resources guarantees, that close() will be called. Even in the
	 * case of an exception in the try-block!
	 * <p>
	 * Therefore try-with-resources is equal to try with close() in a finally
	 * block.
	 */
	private static void closedEvenAfterExceptionInTryBlock() {
		try (MyCloseable mc = new MyCloseable(1)) {
			throw new RuntimeException("danke & auf wiedersehen");
		}
	}

	/**
	 * Now let's have a look at exceptions. In this simple case our resource is
	 * closed, but it throws an exception which is not caught. We see the
	 * exception. So far so good.
	 */
	private static void exceptionInClose() throws FileNotFoundException {
		try (MyExceptionalCloseable mc = new MyExceptionalCloseable(1)) {
			System.err.println(mc.getA());
		}
	}
	
	/**
	 * This is more interesting: the try-block already throws an exception. We
	 * already learnt, that close() will be called anyway and it throws an
	 * exception as well!
	 * <p>
	 * <b>Note:</b> the Exception in close is suppressed!
	 */
	private static void suppressedExceptionInClose() throws FileNotFoundException {
		try (MyExceptionalCloseable mc = new MyExceptionalCloseable(1)) {
			throw new RuntimeException("and then: I panicked!");
		}
	}
	
	/**
	 * When the constructor already throws an exception, the try-block is not
	 * executed, and close() is (of course) not called - we couldn't instantiate
	 * the class.
	 * <p>
	 * But the finally-block is - before throwing the exception. Nothing new,
	 * but noted for the sake of completeness.
	 */
	private static void exceptionInConstructor() throws IOException {
		try (MyExceptionsEverywhereCloseable mc = new MyExceptionsEverywhereCloseable(1)) {
			System.err.println("try-block (this statement) is NOT executed!");
			System.err.println(mc.getA());
		} finally {
			System.err.println("finally..");
		}
		System.err.println("finished!"); // not reached
	}

	/**
	 * The Exception in the constructor can of course be caught..
	 */
	private static void catchExceptionInConstructor() throws IOException {
		try (MyExceptionsEverywhereCloseable mc = new MyExceptionsEverywhereCloseable(1)) {
			System.err.println("try-block (this statement) is NOT executed!");
			System.err.println(mc.getA());
		} catch(IOException ioe) {
			System.err.println("catching...");
			ioe.printStackTrace();
		} finally {
			System.err.println("finally..");
		}
		System.err.println("finished!");
	}

	/**
	 * The same goes for the exception thrown in close()..
	 */
	private static void catchExceptionInClose() throws IOException {
		try (MyExceptionalCloseable mc = new MyExceptionalCloseable(1)) {
			System.err.println(mc.getA());
		} catch(IOException ioe) {
			System.err.println("catching...");
			ioe.printStackTrace();
		} finally {
			System.err.println("finally..");
		}
		System.err.println("finished!");
	}
	
	/**
	 * As demonstrated in <code>variableNotAvailableInCatchOrFinally</code>, the
	 * instance of interest is not available in catch or finally when using
	 * try-with-resources. This means if we want to call
	 * {@link MyExceptionalCloseable#getA()}, we must use the old-school way of
	 * coding.
	 */
	private static void oldSchoolClosing() {
		MyExceptionalCloseable mc = new MyExceptionalCloseable(1);
		System.err.println(mc.getA());

		try {
			mc.close();
		} catch (FileNotFoundException e) {
			System.err.println("catching...");
			e.printStackTrace();
			System.err.println("the value of a has changed to: " + mc.getA());
		} finally {
			System.err.println("finally..");
		}
		System.err.println("finished!");
	}

	
	public static void main(String[] args) throws Exception {
//		simple();
//		multipleResources();
//		closedEvenAfterExceptionInTryBlock();
//		exceptionInClose();
//		suppressedExceptionInClose();
//		exceptionInConstructor();
//		catchExceptionInConstructor();
		catchExceptionInClose();
//		oldSchoolClosing();
	}

}
