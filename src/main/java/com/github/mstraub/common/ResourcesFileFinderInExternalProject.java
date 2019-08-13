
package com.github.mstraub.common;

public class ResourcesFileFinderInExternalProject {
	public static void main(String[] args) {
		boolean found = ResourcesFileFinder.findFile("resources/touchme");
		boolean foundNonStatic = new ResourcesFileFinder().findFile();
		System.out.println("find file in other project's resources: " + found + foundNonStatic);
	}
}
