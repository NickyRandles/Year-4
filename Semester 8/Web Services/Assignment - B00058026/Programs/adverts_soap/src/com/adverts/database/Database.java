package com.adverts.database;

import java.util.HashMap;
import java.util.Map;

import com.adverts.models.Advert;

public class Database {
	
	private static Map<Integer, Advert> adverts = new HashMap<Integer, Advert>();
	
	static{
		adverts.put(1, new Advert(1, "Hello World!", "gdfzfzsdaa", "Nicky"));
		adverts.put(2, new Advert(2, "Hello Java!", "gdsffdgfsdaf", "Nicky"));
	}
	
	public static Map<Integer, Advert> getAdverts(){
		return adverts;
	}
	
}
