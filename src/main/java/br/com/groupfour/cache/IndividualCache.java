package br.com.groupfour.cache;

import java.util.HashMap;

public class IndividualCache {
	
	private HashMap<Object, Object> mapa;
	private String tableName;
	private String key;
	
	public IndividualCache(String tableName, String key) {
		this.mapa = new HashMap<>();
		this.tableName = tableName;
		this.key = key;
	}
	
	public void put(Object object) {
		mapa.put(tableName + key, object);
	}
	
	public Object get() {
		return mapa.get(tableName + key);
	}
	
	

}
