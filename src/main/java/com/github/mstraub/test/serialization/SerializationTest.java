package com.github.mstraub.test.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class SerializationTest implements Serializable {
	A a;
	B b;

	public SerializationTest() {
		a = new A();
		b = new B();
		a.b = b;
		b.a = a;
		a.bs = new HashMap<Integer, B>();
		b.as = new HashMap<Integer, A>();
		a.bs.put(1, b);
		a.bs.put(2, b);
		b.as.put(6, a); // recursion causes StackOverflow!
	}
	
	
	@Override
    public String toString() {
		return a.toString() + "\n" + b.toString();
	}

	public static void main(String[] args) throws Exception {
		SerializationTest test = new SerializationTest();

		File outputFile = new File("/tmp/test.ser");
		if (outputFile.exists())
			outputFile.delete();

		FileOutputStream fileOut = new FileOutputStream(outputFile);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(test);
		out.close();
		fileOut.close();

		FileInputStream fileIn = new FileInputStream(outputFile);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		SerializationTest testRead = (SerializationTest) in.readObject();
		in.close();
		fileIn.close();
		
		System.out.println(test + "\n\n");
		System.out.println(testRead);
	}
}
