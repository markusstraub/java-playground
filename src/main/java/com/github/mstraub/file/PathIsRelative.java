package com.github.mstraub.file;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathIsRelative {
	
	public static void main(String[] args) {
		Path absolute1 = Paths.get("/tmp");
		Path absolute2 = Paths.get("/home");
		Path relative1 = Paths.get("./relative/1");
		Path relative2 = Paths.get("relative/2");
		Path magicTilde = Paths.get("~/inmyhome");
		
		System.out.println("resolved absolute path wins: " + absolute1.resolve(absolute2).toAbsolutePath());
		System.out.println("magicTilde not resolved is problematic: " + absolute1.resolve(magicTilde).toAbsolutePath());
		System.out.println("magicTilde resolving only works in bash, not in Java: " + absolute1.resolve(magicTilde.toAbsolutePath()).toAbsolutePath());
	}

}
