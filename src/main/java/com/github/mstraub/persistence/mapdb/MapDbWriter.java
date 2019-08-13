package com.github.mstraub.persistence.mapdb;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.ConcurrentMap;

import org.mapdb.DB;
import org.mapdb.DBMaker;

public class MapDbWriter {

	final static String DB_PATH = "/tmp/mapdb_example.db";
	final static int ITERATIONS = 50_000; // 136_000;

	public static void main(String[] args) {
		LocalTime startTime = LocalTime.now();
		try (DB db = DBMaker.fileDB(DB_PATH).transactionEnable().make()) {
			@SuppressWarnings("unchecked")
			ConcurrentMap<String, byte[]> map = (ConcurrentMap<String, byte[]>) db.hashMap("map").createOrOpen();
			System.out.println("map size before writing: " + map.size());
			for (int i = 0; i < ITERATIONS; i++) {
				map.put("" + i, ZipUtil.zip(createRandomString(i)));
			}
			db.commit();
			System.out.println("map size after writing: " + map.size());
		}
		System.out.println("writing took " + Duration.between(startTime, LocalTime.now()));
	}

	private static String createRandomString(int id) {
		String millis = " [" + id + "] " + System.currentTimeMillis();
		String result = millis;
		for (int i = 0; i < 11; i++)
			result += result;
//		System.out.println(result.length());
		return result;
	}

}
