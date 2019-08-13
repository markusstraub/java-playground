package com.github.mstraub.parallel;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.CaseFormat;
import com.google.common.math.Stats;
import com.google.common.math.StatsAccumulator;

public class WorkSingleton {

	private static final Logger LOGGER = LoggerFactory.getLogger(WorkSingleton.class);
	private Statistics statistics = new Statistics();

	public void calculate(int seed) {
//		LOGGER.info("start");
		StatisticsItem startItem = statistics.getStartMeasurement();

		cpuIntensive(seed);
		
		statistics.finishMeasurement(startItem);
//		LOGGER.info("finish");
	}

	private void onlyWait() {
		try {
			Thread.sleep(ParallelExecutor.TASK_DURATION_MS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private double cpuIntensive(int seed) {
		double whatever = seed;
		for(int i=0; i<10_000_000; i++) {
			whatever = Math.acos(i + whatever);
		}
		return whatever;
	}

	public void printAndResetStatistics() {
		synchronized (statistics) {
			long count = statistics.count();
			if (count == 0) {
				LOGGER.info("0 calculations");
			} else {
				Map<Statistics.Type, Stats> snapshot = statistics.getSnapshot();
				StringBuilder sb = new StringBuilder(count + " calculation(s); ");
				for (Statistics.Type type : Statistics.Type.values()) {
					Stats stats = snapshot.get(type);
					sb.append(type + ": avg " + Math.round(stats.mean()) + "ms");
//					sb.append(", stddev " + Math.round(stats.populationStandardDeviation()) + "ms);
					sb.append("; ");
				}
				LOGGER.info(sb.toString());
			}
			statistics = new Statistics();
		}
	}

	private static final class Statistics {

		public enum Type {
			WALL_TIME, USER_TIME, CPU_TIME, WAITED_TIME, BLOCKED_TIME;
			@Override
			public String toString() {
				return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, super.toString());
			}
		}

		public final Map<Type, StatsAccumulator> stats;

		public Statistics() {
			this.stats = Stream.of(Type.values())
					.collect(Collectors.toMap(Function.identity(), t -> new StatsAccumulator()));
		}

		/**
		 * Retrieve the start item at the beginning of the code you want to measure
		 */
		public StatisticsItem getStartMeasurement() {
			return makeMeasurement();
		}

		private static StatisticsItem makeMeasurement() {
			long wallTimeMs = System.currentTimeMillis();
			ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
			threadMXBean.setThreadContentionMonitoringEnabled(true);
			long userTimeMs = threadMXBean.getCurrentThreadUserTime() / 1_000_000;
			// TODO sometimes userTime does not start at 0 and we get negative durations.
			// why?
//			System.err.println("user time of thread " + Thread.currentThread().getId() + " is " + userTimeMs);
			long cpuTimeMs = threadMXBean.getCurrentThreadCpuTime() / 1_000_000;
			ThreadInfo threadInfo = threadMXBean.getThreadInfo(Thread.currentThread().getId());
			return new StatisticsItem(wallTimeMs, userTimeMs, cpuTimeMs, threadInfo);
		}

		public synchronized void finishMeasurement(StatisticsItem startItem) {
			StatisticsItem endItem = makeMeasurement();
			for (Entry<Type, StatsAccumulator> entry : stats.entrySet()) {
				switch (entry.getKey()) {
				case BLOCKED_TIME:
					entry.getValue().add(endItem.threadInfo.getBlockedTime() - startItem.threadInfo.getBlockedTime());
					break;
				case CPU_TIME:
					entry.getValue().add(endItem.cpuTimeMs - startItem.cpuTimeMs);
					break;
				case USER_TIME:
					entry.getValue().add(endItem.userTimeMs - startItem.userTimeMs);
					break;
				case WAITED_TIME:
					entry.getValue().add(endItem.threadInfo.getWaitedTime() - startItem.threadInfo.getWaitedTime());
					break;
				case WALL_TIME:
					entry.getValue().add(endItem.wallTimeMs - startItem.wallTimeMs);
					break;
				default:
					throw new IllegalArgumentException("unknown type " + entry.getKey());
				}
			}
		}

		public synchronized long count() {
			return this.stats.get(Type.WALL_TIME).count();
		}

		public synchronized Map<Type, Stats> getSnapshot() {
			return this.stats.entrySet().stream()
					.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().snapshot()));
		}
	}

	private static class StatisticsItem {
		public final long wallTimeMs, userTimeMs, cpuTimeMs;
		public final ThreadInfo threadInfo;

		public StatisticsItem(long wallTimeMs, long userTimeMs, long cpuTimeMs, ThreadInfo threadInfo) {
			this.wallTimeMs = wallTimeMs;
			this.userTimeMs = userTimeMs;
			this.cpuTimeMs = cpuTimeMs;
			this.threadInfo = threadInfo;
		}
	}

}
