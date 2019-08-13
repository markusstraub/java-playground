package com.github.mstraub.common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriting {
	/**
	 * Here we demonstrate that wrapping a FileWriter and then closing
	 * the wrapper will also close the original FileWriter.
	 */
	public static void main(String[] args) throws IOException {
		FileWriter fileW = new FileWriter("/tmp/test_7893cfja03");
		BufferedWriter bufW = new BufferedWriter(fileW);
		bufW.write("teststring");
		bufW.close();
		fileW.write("teststring2"); // fails because stream is already closed!
	}
}
