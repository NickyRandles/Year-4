package com.adverts.resources;

import java.util.ArrayList;
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

import com.adverts.models.Comment;
import com.adverts.services.CommentService;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

	private CommentService commentService = new CommentService();
	
	@GET
	public List<Comment> getAllComments(@PathParam("advertId") int advertId){
		return new ArrayList<Comment>(commentService.getAllComments(advertId));
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("advertId") int advertId, @PathParam("commentId") int commentId){
		return commentService.getComment(advertId, commentId);
	}
	
	@POST
	public Comment addComment(@PathParam("advertId") int advertId, Comment comment){
		return commentService.addComment(advertId, comment);
	}
	
	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("advertId") int advertId, @PathParam("commentId") int commentId, Comment comment){
		comment.setId(commentId);
		return commentService.updateComment(advertId, comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public void removeComment(@PathParam("advertId") int advertId, @PathParam("commentId") int commentId){
		commentService.removeComment(advertId, commentId);
	}
}
