package com.github.mstraub.devbrc.boundaries;

import java.util.HashMap;
import java.util.Map;

/**
 * Encapsulate a collection of sensors
 */
public class Sensors {
	private Map<String, Sensor> sensors = new HashMap<>();
	public Sensor getById(String id) {
		return sensors.get(id);
	}
	// snip
}
