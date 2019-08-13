package com.github.mstraub.persistence.mapdb;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.ConcurrentMap;

import org.mapdb.DB;
import org.mapdb.DBMaker;

// unzipped: 50000 random accesses took PT8.448S
// zipped: 50000 random accesses took PT9.772S
public class MapDbReader {

	public static void main(String[] args) {
		LocalTime startTime = LocalTime.now();
		try (DB db = DBMaker.fileDB(MapDbWriter.DB_PATH).transactionEnable().make()) {
			@SuppressWarnings("unchecked")
			ConcurrentMap<String, byte[]> map = (ConcurrentMap<String, byte[]>) db.hashMap("map").createOrOpen();
			System.out.println("map size: " + map.size());
			Random random = new Random(0);
			for (int i = 0; i < MapDbWriter.ITERATIONS; i++) {
				int nextIndex = random.nextInt(MapDbWriter.ITERATIONS);
				String value = ZipUtil.unzip(map.get("" + nextIndex));
//				System.out.println(nextIndex + ": " + value.substring(0, 30));
			}
		}
		System.out.println(
				MapDbWriter.ITERATIONS + " random accesses took " + Duration.between(startTime, LocalTime.now()));
	}

}
