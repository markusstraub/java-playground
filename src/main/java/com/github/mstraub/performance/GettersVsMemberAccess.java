package com.github.mstraub.performance;

public class GettersVsMemberAccess {
	public int value = 42;

	public int getValue() {
		return value;
	}

	public static void main(String[] args) {
		GettersVsMemberAccess test = new GettersVsMemberAccess();
		int dummy;
//		for (int i = 0; i < 1000000; i++) {
//			dummy = test.value;
//			dummy = test.getValue();
//		}

		long rounds = 2000000000l;
		long sumDirekt2 = 0L;
		long tDirektStart2 = System.currentTimeMillis();
		for (int i = 0; i < rounds; i++) {
			sumDirekt2 += test.value;
		}
		long tDirekt2 = System.currentTimeMillis() - tDirektStart2;
		long sumGetter = 0L;
		long tGetterStart = System.currentTimeMillis();
		for (int i = 0; i < rounds; i++) {
			sumGetter += test.getValue();
		}
		long tGetter = System.currentTimeMillis() - tGetterStart;
		
		long tDirektStart = System.currentTimeMillis();
		long sumDirekt = 0L;
		for (int i = 0; i < rounds; i++) {
			sumDirekt += test.value;
		}
		long tDirekt = System.currentTimeMillis() - tDirektStart;

		

		System.out.println("Direkt: " + tDirekt + " " + sumDirekt);
		System.out.println("Getter: " + tGetter + " " + sumGetter);
		System.out.println("Direkt2: " + tDirekt2 + " " + sumDirekt2);
	}
}
