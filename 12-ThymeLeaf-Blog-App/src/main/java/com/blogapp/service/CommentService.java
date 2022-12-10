package com.blogapp.service;

import java.util.List;

import com.blogapp.dto.CommentDto;

public interface CommentService {
	
	public void createComment(String postURL, CommentDto comment);

	public List<CommentDto> findALLComments();

	public void deleteComment(Long commentId);
	
	public List<CommentDto> findCommentByUser();

}
