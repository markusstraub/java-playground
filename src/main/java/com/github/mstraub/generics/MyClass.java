package com.github.mstraub.generics;

public class MyClass implements MyInterface<Double> {

	@Override
	public Double getValue() {
		return new Double(12);
	}
	

}
