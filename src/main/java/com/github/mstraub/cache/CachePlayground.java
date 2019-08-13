package com.github.mstraub.cache;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CachePlayground {

    public static void main(String[] args) {

        Cache<Integer, String> objectCache = CacheBuilder.newBuilder().maximumSize(3)
                .expireAfterWrite(10, TimeUnit.MINUTES).build();

        for (int i = 0; i < 100; i++) {
            objectCache.put(i, "thevalueis" + i);
        }

        for (int i = 0; i < 100; i++) {
            System.out.println(i + " in cache: " + objectCache.getIfPresent((Integer) i));
        }
    }

}
