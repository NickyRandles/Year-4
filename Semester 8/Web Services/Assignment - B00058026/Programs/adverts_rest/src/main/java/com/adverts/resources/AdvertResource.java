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

import com.adverts.models.Advert;
import com.adverts.services.AdvertService;


@Path("/adverts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdvertResource {

	private AdvertService advertService = new AdvertService();
	
	@GET 
	public List<Advert> getAdverts(){		
		return advertService.getAdverts();	
	}
	
	@GET
	@Path("/{advertId}")
	public Advert getAdvert(@PathParam("advertId") int id){
		Advert advert = advertService.getAdvert(id);
		return advert;
	}

	@POST
	public Advert addAdvert(Advert advert){
		return advertService.addAdvert(advert);
	}
	
	@PUT
	@Path("/{advertId}")
	public Advert updateAdvert(@PathParam("advertId") int id, Advert advert){
		advert.setId(id);
		return advertService.updateAdvert(advert);
	}
	
	@DELETE
	@Path("/{advertId}")
	public Advert removeAdvert(@PathParam("advertId") int id){
		return advertService.removeAdvert(id);
	}
	
	@Path("/{advertId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
}
