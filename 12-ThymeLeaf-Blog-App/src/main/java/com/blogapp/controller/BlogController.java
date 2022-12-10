package com.blogapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogapp.dto.CommentDto;
import com.blogapp.dto.PostDto;
import com.blogapp.entity.Comment;
import com.blogapp.service.PostService;

@Controller
public class BlogController {
	
	private PostService postService;

	//After spring 4.3 no need to give autowire, if we have only one parameter in constructor mean spring automatically inject
	public BlogController(PostService postService) {
		this.postService = postService;
	}
	
	//Handler Method for Root URL
	@GetMapping("/")
	public String getAllPosts(Model model) {
		List<PostDto> posts = postService.findAllPosts();
		model.addAttribute("postResponse", posts);
		System.out.println("post in Blog :" +posts);
		return "blog/view_posts";
		
	}
	
	//Handler Method for View Post
	@GetMapping("/post/{postUrl}")
	public String viewPost(@PathVariable("postUrl") String postUrl, Model model) {
		CommentDto commentDto = new CommentDto();
		PostDto post = postService.findPostByUrl(postUrl);
		model.addAttribute("post", post);
		model.addAttribute("comment", commentDto);
		return "blog/blog_view";
	}
	
	//Hnadler Method for SearchBlog
	@GetMapping("/page/search")
	public String searchPost(@RequestParam("query") String query,Model model) {
		List<PostDto> searchPost = postService.searchPost(query);
		model.addAttribute("postResponse", searchPost);
		return "blog/view_posts";
	}

}
