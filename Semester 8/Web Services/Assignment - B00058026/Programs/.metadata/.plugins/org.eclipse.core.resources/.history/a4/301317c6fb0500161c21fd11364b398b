package com.adverts.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.adverts.database.Database;
import com.adverts.models.Advert;
import com.adverts.models.Comment;


public class CommentService {
	
	private Map<Integer, Advert> adverts = Database.getAdverts();
	
	public List<Comment> getAllComments(int advertId){
		Map<Integer, Comment> comments = adverts.get(advertId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(int advertId, int commentId){
		Map<Integer, Comment> comments = adverts.get(advertId).getComments();
		return comments.get(commentId);
	}
	
	public Comment addComment(int advertId, Comment comment){
		Map<Integer, Comment> comments = adverts.get(advertId).getComments();
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment updateComment(int advertId, Comment comment){
		Map<Integer, Comment> comments = adverts.get(advertId).getComments();
		if(comment.getId() <= 0){
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment removeComment(int advertId, int commentId){
		Map<Integer, Comment> comments = adverts.get(advertId).getComments();
		return comments.remove(commentId);
	}	
	
}
