package com.blogapp.service;

import java.util.List;

import com.blogapp.dto.PostDto;

public interface PostService {
	
	public List<PostDto> findAllPosts();
	
	public void createPost(PostDto post);
	
	public PostDto findByPostId(Long postId);
	
	public void updatePost(PostDto post);

	public void deletePostById(Long postId);

	public PostDto findPostByUrl(String postUrl);

	public List<PostDto> searchPost(String keyword);
	
	public List<PostDto> findPostByUser();
}
