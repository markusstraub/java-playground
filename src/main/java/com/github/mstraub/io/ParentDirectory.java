package com.github.mstraub.io;

import java.nio.file.Paths;

public class ParentDirectory {
	
	public static void main(String[] args) {
		System.out.println(Paths.get("/"));
		System.out.println(Paths.get("/").getParent());
	}

}
