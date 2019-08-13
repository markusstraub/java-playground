package com.github.mstraub.basejava;

import java.util.ArrayList;
import java.util.List;

public class AutoBoxing {
	public static void main(StringStuff[] args) {
		List<Integer> ints = new ArrayList<Integer>();
		ints.add(1);
		System.out.println(ints.contains(1));

		
		Integer i = new Integer(2);
		ints.add(i);
		System.out.println(ints.contains(2));
		System.out.println(ints.contains(new Integer(2)));
	}
}
