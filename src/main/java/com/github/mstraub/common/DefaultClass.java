package com.github.mstraub.common;
import java.util.ArrayList;
import java.util.Collection;


public class DefaultClass {
	protected int value;
	protected Collection collection;
	
	public DefaultClass() {
		System.out.println("DefaultClass constructor sez hi");
		value = 23;
		collection = new ArrayList<Integer>();
	}
	
	public int getValue() {
		return value;
	}
	
	public Collection getCollection() {
		return collection;
	}
}
