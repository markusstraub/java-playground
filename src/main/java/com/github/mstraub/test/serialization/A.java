package com.github.mstraub.test.serialization;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

public class A implements Serializable {
	public String s = "a";
	public B b;
	public HashMap<Integer, B> bs;
	
	@Override
    public String toString() {
		return s + b.s + Arrays.toString(bs.values().toArray(new B[0]));
	}
}
