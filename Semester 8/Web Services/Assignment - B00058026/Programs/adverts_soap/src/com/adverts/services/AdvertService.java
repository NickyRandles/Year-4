package com.adverts.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.adverts.database.Database;
import com.adverts.models.Advert;

public class AdvertService {

	private Map<Integer, Advert> adverts = Database.getAdverts();
	
	public List<Advert> getAdverts(){
		return new ArrayList<Advert>(adverts.values());
	}
	
	public Advert getAdvert(int id){
		return adverts.get(id);
	}
	
	public Advert addAdvert(String name, String description, String author){
		Advert advert = new Advert(adverts.size() + 1, name, description, author);
		adverts.put(advert.getId(), advert);
		return advert;
	}
	
	public Advert updateAdvert(int id, String name, String description, String author){
		if(id <= 0){
			return null;
		}
		Advert advert = new Advert(id, name, description, author);
		adverts.put(id, advert);
		return advert;
	}
	
	public Advert removeAdvert(int id){
		return adverts.remove(id);
	}
}
