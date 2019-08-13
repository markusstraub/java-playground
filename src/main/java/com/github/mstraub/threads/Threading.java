package com.github.mstraub.threads;

import java.util.concurrent.TimeUnit;

/**
 * How long does this Class run?
 * <p>
 * Probably one second, probably forever, because stopRequested is neither
 * volatile nor is any of the threads synchronized on the varible. The JVM does
 * not guarantee that changes in one thread are visible in the other thread.
 * 
 * @author mstraub
 * 
 */
public class Threading {
	private static boolean stopRequested;

	public static void main(String[] args) throws InterruptedException {
		Thread backgroundThread = new Thread(new Runnable() {
			@Override
            public void run() {
				int i = 0;
				while (!stopRequested) {
					// System.err.println(i);
					i++;
				}
			}
		});

		backgroundThread.start();
		// System.err.println("start");
		TimeUnit.SECONDS.sleep(1);
		// System.err.println("sleep over");
		stopRequested = true;
		// System.err.println("stop requested");
	}
}