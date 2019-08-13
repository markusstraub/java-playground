package com.github.mstraub.speedtest;

public class SpeedTest {
	
	static StringBuffer sb = new StringBuffer();
	
	public static String getStringFromStringBuffer(int a, int b, int c) {
		StringBuffer sb = new StringBuffer();
		sb.append(a).append("_").append(b).append("_")
				.append(c);
		return sb.toString();
	}
	
	public static String getStringFromStringBuffer2(int a, int b, int c) {
		sb.delete(0, sb.length());
		sb.append(a).append("_").append(b).append("_")
				.append(c);
		return sb.toString();
	}
	
	public static String getStringFromConcatenation(int a, int b, int c) {
		return a + "_" + b + "_" + c;
	}

	
	
	public static void main(String[] args) {
		int runs = 5000000;
		long millis;
		
		millis = System.currentTimeMillis();
		for(int i=0; i<runs; i++)
			getStringFromStringBuffer(i*2, i+2, i*10);
		System.out.println("stringbuffer: " + (System.currentTimeMillis() - millis));
		
		millis = System.currentTimeMillis();
		for(int i=0; i<runs; i++)
			getStringFromStringBuffer2(i*2, i+2, i*10);
		System.out.println("stringbuffer2: " + (System.currentTimeMillis() - millis));
		
		millis = System.currentTimeMillis();
		for(int i=0; i<runs; i++)
			getStringFromConcatenation(i*2, i+2, i*10);
		System.out.println("concatenation: " + (System.currentTimeMillis() - millis));
	}
	
}
