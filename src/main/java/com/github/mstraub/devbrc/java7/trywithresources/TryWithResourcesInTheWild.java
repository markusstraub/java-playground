package com.github.mstraub.devbrc.java7.trywithresources;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * http://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.
 * html
 */
public class TryWithResourcesInTheWild {

	private static String readFile(String path) throws IOException {
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = null;
		while ((line = br.readLine()) != null)
			sb.append(line);
		br.close();
		return sb.toString();
	}

	private static String readFileJava7(String path) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			StringBuffer sb = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null)
				sb.append(line);
			return sb.toString();
		}
	}

	/**
	 * If the methods readLine and close both throw exceptions, then this method
	 * throws the exception thrown from the finally block; the exception thrown
	 * from the try block is suppressed
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 *             if file can not be read
	 */
	private static String readFirstLineFromFile(String path) {
		BufferedReader br = null;
		String line = null;
		try {
			 br = new BufferedReader(new FileReader(path));
			line = br.readLine(); // <-- possibly suppressed exception
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return line;
	}

	/**
	 * If exceptions are thrown from both the try block and the
	 * try-with-resources statement, then this method throws the exception
	 * thrown from the try block; the exception thrown from the
	 * try-with-resources block is suppressed.
	 */
	private static String readFirstLineFromFileJava7(String path) throws IOException {
		// br will be closed regardless of whether the try statement
		// completes normally or abruptly
		try (
				BufferedReader br = new BufferedReader(new FileReader(path))
				// ^-- possibly suppressed exception
			) {
			return br.readLine();
		}
		// any catch or finally here is run *after* the declared
		// resources haven been closed
	}
	
	public static void main(String[] args) throws IOException {
		String path = "resources/data.txt";

		System.out.println(readFirstLineFromFile(path));
		
		try {
			System.out.println(readFirstLineFromFileJava7(path));
		} catch(IOException e) {
			// in Java7 suppressed exceptions can be gathered
			Throwable[] suppressed = e.getSuppressed();
			for(Throwable t : suppressed)
				t.printStackTrace();
		}

		// System.out.println(readFile(path));
		// System.out.println(readFileJava7(path));

	}
}
