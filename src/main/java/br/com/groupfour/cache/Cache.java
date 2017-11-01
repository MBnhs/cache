package br.com.groupfour.cache;

import java.util.HashMap;

public class Cache {
	
	private HashMap<Object, Object> mapa;
	
	private Cache() {
		mapa = new HashMap<>();
	}
	
	private static class CacheHolder {
        private static final Cache INSTANCE = new Cache();
    }
	
	public static Cache getInstance() {
		return CacheHolder.INSTANCE; 
	}
	
	public void put(Object key, Object value) {
		mapa.put(key, value);
	}
	
	public Object get(Object key) {
		return mapa.get(key);
	}
	
	public void clear() {
		mapa.clear();
	}

}
