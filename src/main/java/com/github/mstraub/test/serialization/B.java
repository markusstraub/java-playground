package com.github.mstraub.test.serialization;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

public class B implements Serializable {
	public String s = "b";
	public A a;
	public HashMap<Integer, A> as;
	
	@Override
    public String toString() {
		return s + a.s + Arrays.toString(as.values().toArray(new A[0]));
	}
}
