package br.com.groupfour.cache;

import java.util.HashMap;

public class CacheMinor {
	
	private HashMap<Object, Object> mapa;
	
	public CacheMinor() {
		mapa = new HashMap<>();
	}
	
	public void put(Object key, Object value) {
		mapa.put(key, value);
	}

	public Object get(Object object) {
		return mapa.get(object);
	}

	public void clear() {
		mapa.clear();
	}

}
