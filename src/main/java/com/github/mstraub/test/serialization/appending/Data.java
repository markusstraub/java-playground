package com.github.mstraub.test.serialization.appending;

public class Data implements PersistentDataCache.CachableItem {
	private static final long serialVersionUID = 1L;
	private String key;
	private transient int magicNumberTransient;
	private String poem;

	public Data(String key, String poem) {
		super();
		this.key = key;
		this.magicNumberTransient = key.hashCode();
		this.poem = poem;
	}

	@Override
	public String getKey() {
		return key;
	}

	public int getMagicNumberTransient() {
		return magicNumberTransient;
	}

	public String getPoem() {
		return poem;
	}

	@Override
	public String toString() {
		return "Data [key=" + key + ", magicNumberTransient=" + magicNumberTransient + ", poem=" + poem + "]";
	}

}
