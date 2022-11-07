package com.hkbank.pbcrs.cache;

import java.util.HashMap;
import java.util.Map;

public class CacheManager {
	private static Map<String, Object> cacheMap = new HashMap<String, Object>();

	// 单实例构造方法
	private CacheManager() {
		super();
	}

	// 得到缓存。同步静态方法
	public static Object getCache(String key) {
		return cacheMap.get(key);
	}

	// 判断是否存在一个缓存
	public static boolean hasCache(String key) {
		return cacheMap.containsKey(key);
	}

	// 清除所有缓存
	public synchronized static void clearAll() {
		if (!cacheMap.isEmpty()) {
			cacheMap.clear();
		}
	}

	// 清除指定的缓存
	public synchronized static void clearOnly(String key) {
		if (cacheMap.containsKey(key)) {
			cacheMap.remove(key);
		}
	}

	// 载入缓存
	public synchronized static void putCache(String key, Object data) {
		cacheMap.put(key, data);
	}

	// 获取缓存大小
	public static int getCacheSize() {
		return cacheMap.size();
	}

}
