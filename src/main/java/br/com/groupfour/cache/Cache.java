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
	
	public void put(Object object) {
		mapa.put(object, object);
	}
	
	public Object get(Object object) {
		return mapa.get(object);
	}
	
	public void clear() {
		mapa.clear();
	}

}
