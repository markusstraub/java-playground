package com.github.mstraub.common;

import java.io.File;

public class ResourcesFileFinder {
	public static void main(String[] args) {
		System.out.println("find file in project's resources: " + findFile("resources/touchme"));
	}
	
	public static boolean findFile(String fileName) {
		return new File(fileName).exists();
	}
	
	public boolean findFile() {
		return new File("resources/touchme").exists();
	}
}
