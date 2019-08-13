package com.github.mstraub.devbrc.boundaries;

import java.util.Map;

public class Main {
	public static void main(String[] args) {
		funWithSensors(null, "");
		funWithSensorMap(null, "");
	}
	
	private static void funWithSensors(Sensors sensors, String id) {
		Sensor s = sensors.getById("sensor1");
		//..
	}
	
	private static void funWithSensorMap(Map<String, Sensor> sensors, String id) {
		Sensor s = sensors.get(id);
		//..
		sensors.put("xx", new Sensor()); // 
		sensors.clear();
	}
}
