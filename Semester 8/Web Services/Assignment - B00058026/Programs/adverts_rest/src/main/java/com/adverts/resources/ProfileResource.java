package com.adverts.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.adverts.models.Profile;
import com.adverts.services.ProfileService;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {

	private ProfileService profileService = new ProfileService();
	
	@GET
	public List<Profile> getProfiles(){
		return profileService.getAllProfiles();
	}
	
	@GET
	@Path("/{username}")
	public Profile getProfile(@PathParam("username") String username){
		return profileService.getProfile(username);
	}
	
	@POST
	public Profile addProfile(Profile profile){
		System.out.print(profile);
		return profileService.addProfile(profile);
	}
	
	@PUT
	@Path("/{username}")
	public Profile updateProfile(@PathParam("username") String username, Profile profile){
		profile.setUsername(username);
		return profileService.updateprofile(profile);
	}
	
	@DELETE
	@Path("/{username}")
	public void removeProfile(@PathParam("username") String username){
		profileService.removeprofile(username);
	}
}
