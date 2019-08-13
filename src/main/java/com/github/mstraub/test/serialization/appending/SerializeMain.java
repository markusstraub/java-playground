package com.github.mstraub.test.serialization.appending;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SerializeMain {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Path cacheFile = Files.createTempFile("poems", ".ser");
		PersistentDataCache<Data> cache = new PersistentDataCache<>(cacheFile);

		System.out.println(cache.getItem("1"));

		cache.addItem(new Data("1", "At times it's like there is a small planet"));
		cache.addItem(new Data("2", "inside me. And on this planet,"));
		cache.addItem(new Data("3", "there are many small wars, yet none"));
		cache.addItem(new Data("4", "big enough to make a real difference."));

		System.out.println(cache.getItem("1"));
	}

}
