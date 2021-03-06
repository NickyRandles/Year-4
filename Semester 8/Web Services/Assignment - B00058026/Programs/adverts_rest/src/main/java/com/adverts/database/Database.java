package com.adverts.database;

import java.util.HashMap;
import java.util.Map;

import com.adverts.models.Advert;
import com.adverts.models.Comment;
import com.adverts.models.Profile;

public class Database {
	
	private static Map<String, Profile> profiles = new HashMap<String, Profile>();
	private static Map<Integer, Advert> adverts = new HashMap<Integer, Advert>();
	private static Map<Integer, Comment> comments = new HashMap<Integer, Comment>();
	
	static{
		adverts.put(1, new Advert(1, "Hello World!", "gdfzfzsdaa", "Nicky"));
		adverts.put(2, new Advert(2, "Hello Java!", "gdsffdgfsdaf", "Nicky"));
	}
	
	public static Map<String, Profile> getProfiles(){
		return profiles;
	}
	
	public static Map<Integer, Advert> getAdverts(){
		return adverts;
	}
	
	public static Map<Integer, Comment> getComments(){
		return comments;
	}
}
