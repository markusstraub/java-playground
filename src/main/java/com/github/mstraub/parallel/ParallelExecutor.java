package com.github.mstraub.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParallelExecutor {

	public static final long TASK_DURATION_MS = 138;
	private static final Logger LOGGER = LoggerFactory.getLogger(ParallelExecutor.class);

	public static void main(String[] args) {
		parallelExecution(200, 4);
	}

	private static void parallelExecution(int taskCount, int threadCount) {
		long startTime = System.currentTimeMillis();

		WorkSingleton singleton = new WorkSingleton();
		ExecutorService executor = Executors.newFixedThreadPool(threadCount);

		List<Future<?>> futures = new ArrayList<>();
		for (int i = 0; i < taskCount; i++) {
			final int seed = i;
			Future<?> future = executor.submit(new Runnable() {
				@Override
				public void run() {
					singleton.calculate(seed);
				}
			});
			futures.add(future);
		}
		try {
			executor.shutdown();
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		futures.stream().forEach(f -> {
			try {
				f.get();
			} catch (InterruptedException e) {
				LOGGER.warn("pre-generation was interrupted: {}", e.getMessage());
			} catch (ExecutionException e) {
				LOGGER.warn("pre-generation failed: {}", e.getMessage());
				e.printStackTrace();
			}
		});

		singleton.printAndResetStatistics();
		long totalTime = System.currentTimeMillis() - startTime;
		LOGGER.info("total duration with overheads: {}ms", totalTime);
		LOGGER.info("avg duration per thread with overheads: {}ms", totalTime / threadCount);
		LOGGER.info("threads: {}", threadCount);
		LOGGER.info("speedup: {}", (taskCount * TASK_DURATION_MS) / (double) totalTime);
	}

}
