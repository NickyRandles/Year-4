package com.adverts.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.adverts.database.Database;
import com.adverts.models.Profile;

public class ProfileService {

	private Map<String, Profile> profiles = Database.getProfiles();
	
	public ProfileService(){
		profiles.put("nicky1", new Profile(1, "nicky1", "Nicky", "Randles"));
		profiles.put("john1", new Profile(2, "john1", "John", "Smith"));
	}
	
	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String username){
		return profiles.get(username);
	}
	
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getUsername(), profile);
		return profile;
	}
	
	public Profile updateprofile(Profile profile){
		if(profile.getUsername().isEmpty()){
			return null;
		}
		profiles.put(profile.getUsername(), profile);
		return profile;
	}
	
	public Profile removeprofile(String username){
		return profiles.remove(username);
	}
}
