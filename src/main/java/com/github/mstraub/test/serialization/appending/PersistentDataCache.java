package com.github.mstraub.test.serialization.appending;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.mstraub.test.serialization.appending.PersistentDataCache.CachableItem;

public class PersistentDataCache<T extends CachableItem> {
	private static Logger LOGGER = LoggerFactory.getLogger(PersistentDataCache.class);

	private static final String MARKER = PersistentDataCache.class.getName();

	private Map<String, T> cache = new ConcurrentHashMap<>();
	private AtomicInteger cacheHits = new AtomicInteger(0);
	private AtomicInteger cacheMisses = new AtomicInteger(0);
	private Path cacheFile;
	private boolean cacheFileActive;

	/**
	 * @param cacheFile either a non-existing file (will be created) or an existing
	 *                  valid cache file. If the file is not identified as cache
	 *                  file it won't be changed and persisting does not work.
	 */
	public PersistentDataCache(Path cacheFile) {
		this.cacheFile = cacheFile;
		this.cacheFileActive = true;

		if (Files.exists(cacheFile)) {
			if (!readCacheFromFile()) {
				stopPersistence();
			}
		} else {
			if (!initializeCacheFile()) {
				stopPersistence();
			}
		}
	}

	private boolean readCacheFromFile() {
		try (ObjectInputStream is = new ObjectInputStream(
				new GZIPInputStream(new FileInputStream(cacheFile.toFile())))) {
			if (!is.readUTF().equals(MARKER)) {
				LOGGER.warn("cache file {} is incompatible (wrong marker)", cacheFile);
				return false;
			}
			try {
				while (true) {
					@SuppressWarnings("unchecked")
					T item = (T) is.readObject();
					cache.put(item.getKey(), item);
				}
			} catch (EOFException e) {
				// intentionally read until end of file (EOF)
			}
			return true;
		} catch (ClassCastException | ClassNotFoundException | IOException e) {
			LOGGER.warn("{} while reading cache file '{}': {}", e.getClass().getName(), cacheFile, e.getMessage());
			return false;
		}
	}

	private boolean initializeCacheFile() {
		try (ObjectOutputStream os1 = new ObjectOutputStream(
				new GZIPOutputStream(new FileOutputStream(cacheFile.toFile())))) {
			// initialize serialized file force writing of stream header to avoid
			// java.io.StreamCorruptedException:
			os1.writeUTF(MARKER);
			return true;
		} catch (IOException e) {
			LOGGER.warn("{} while creating cache file '{}': {}", e.getClass().getName(), cacheFile, e.getMessage());
			return false;
		}
	}

	private void stopPersistence() {
		this.cacheFileActive = false;
		LOGGER.warn("stopping persistence of cache - nothing will be written to '{}'", cacheFile);
	}

	public void addItem(T item) {
		cache.put(item.getKey(), item);
		if (cacheFileActive) {
			if (!persistEntryToFile(item)) {
				stopPersistence();
			}
		}
	}

	private boolean persistEntryToFile(T data) {
		try (ObjectOutputStream os1 = new ObjectOutputStreamWithoutHeader(
				new GZIPOutputStream(new FileOutputStream(cacheFile.toFile(), true)))) {
			// initialize serialized file force writing of stream header to avoid
			// java.io.StreamCorruptedException:
			os1.writeObject(data);
			return true;
		} catch (IOException e) {
			LOGGER.warn("{} while writing item to cache file '{}': {}", e.getClass().getName(), cacheFile,
					e.getMessage());
			return false;
		}
	}

	public Optional<T> getItem(String key) {
		if (cache.containsKey(key)) {
			cacheHits.incrementAndGet();
			LOGGER.debug("cache hit for '{}' ({} misses, {} hits)", key, cacheMisses, cacheHits);
			return Optional.of(cache.get(key));
		} else {
			cacheMisses.incrementAndGet();
			LOGGER.debug("cache miss for '{}' ({} misses, {} hits)", key, cacheMisses, cacheHits);
			return Optional.empty();
		}
	}

	public int size() {
		return cache.size();
	}

	private static class ObjectOutputStreamWithoutHeader extends ObjectOutputStream {
		public ObjectOutputStreamWithoutHeader(OutputStream out) throws IOException {
			super(out);
		}

		@Override
		protected void writeStreamHeader() throws IOException {
			reset();
		}
	}

	public static interface CachableItem extends Serializable {
		String getKey();
	}

}