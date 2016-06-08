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
	
	public Advert addAdvert(Advert advert){
		advert.setId(adverts.size() + 1);
		adverts.put(advert.getId(), advert);
		return advert;
	}
	
	public Advert updateAdvert(Advert advert){
		if(advert.getId() <= 0){
			return null;
		}
		adverts.put(advert.getId(), advert);
		return advert;
	}
	
	public Advert removeAdvert(int id){
		return adverts.remove(id);
	}
}
