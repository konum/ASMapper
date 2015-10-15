package org.asmapper.core;

import java.util.HashMap;

import org.asmapper.example.mappers.BrandMap;
import org.asmapper.example.mappers.WheelMap;

/*
 * This class is used to know wich mapper to use for a dto or entity class
 */

@SuppressWarnings("rawtypes")
public class MappersStore {
	private static HashMap<Class, DtoEntityMapInterface> mapperMap = new HashMap<Class, DtoEntityMapInterface>();
	private static boolean mappersLoaded = false;
	private MappersStore(){
	}
	/**
	 * This is a kind of shitty way to ensure we have all mappers loaded on the store. This is needed as we don't knoe prior hand 
	 * what nested mappers a given map may need
	 */
	static void init() {
		if (!mappersLoaded){
			BrandMap.MAP.hashCode();
			WheelMap.MAP.hashCode();
			BrandMap.MAP.hashCode();
			mappersLoaded = true;
		}
	}
	
	static void put(Class clazz, DtoEntityMapInterface value){
		mapperMap.put(clazz, value);
	}
	
	static DtoEntityMapInterface get(Class clazz){
		init();
		return mapperMap.get(clazz);
	}
}
