package com.github.mstraub.basejava;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;

public class SerializeEnum {
	
	enum MyEnum {A, B, C};
	
	public static void main(String[] args) throws IOException {
		MyEnum e = MyEnum.A;
		
		ObjectOutputStream oout = new ObjectOutputStream(Files.newOutputStream(Files.createTempFile(null, null)));
		oout.writeObject(e);
	}
	

}
