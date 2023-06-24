package com.blogapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blogapp.dto.CommentDto;
import com.blogapp.dto.PostDto;
import com.blogapp.service.CommentService;
import com.blogapp.service.PostService;
import com.blogapp.utils.ROLE;
import com.blogapp.utils.SecurityUtils;

@Controller
public class PostController {

	//@Autowired
	private PostService postService;

	@Autowired
	private CommentService commentService;
	
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	@ResponseBody
	public String getposts() {
		return "sample check";
	}
	
	//Create Handler method for Get Request and return Model and view
	@GetMapping("/admin/posts")
	public String posts(Model model){
	
		List<PostDto> allPosts = null;
		String role = SecurityUtils.getRole();
		if(ROLE.ROLE_ADMIN.name().equals(role)) {
			 allPosts = postService.findAllPosts();
		}
		else {
			allPosts = postService.findPostByUser();
		}
		model.addAttribute("posts", allPosts);
		return "/admin/posts";
	}
	
	//Create Handle Method for New Post link
	@GetMapping("/admin/posts/newposts")
	public String newPosts(Model model) {
		
		PostDto postDto = new PostDto();
		model.addAttribute("post", postDto);
		return "/admin/create_post";
	}
	
	//handler method to handle form submit in Creating post request
	@PostMapping("/admin/posts")
	public String createPost(@Valid @ModelAttribute("post") PostDto postDto, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("post", postDto);
			return "/admin/create_post";
		}
		
		postDto.setUrl(getUrl(postDto.getTitle()));
		postService.createPost(postDto);
		return "redirect:/admin/posts";
	}
	
	//handler Method for Edit post request
	@GetMapping("/admin/posts/{postId}/edit")
	public String editPostById(@PathVariable("postId") Long postId, Model model) {
		PostDto postByID = postService.findByPostId(postId);
		model.addAttribute("post", postByID);
		return "/admin/edit_post";
		
	}
	
	//handler Method to handle edit post form submit request
	@PostMapping("/admin/posts/{postId}")
	public String updatePost(@PathVariable("postId") Long postId,
							 @Valid @ModelAttribute("post") PostDto post,
							 BindingResult result, 
							 Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("post", post);
			return "/admin/edit_post";
		}
		post.setId(postId);
		postService.updatePost(post);
		return "redirect:/admin/posts";
		
	}
	
	//Handler method to handle Delete Request
	@GetMapping("/admin/posts/{postId}/delete")
	public String deletePost(@PathVariable("postId") Long postId) {
		postService.deletePostById(postId);
		return "redirect:/admin/posts";
		
	}
	
	//Handler Method for View Post
	@GetMapping(value = "/admin/posts/{postUrl}/view")
	public String viewPost(@PathVariable("postUrl") String postUrl,Model model) {
		
		PostDto postDto = postService.findPostByUrl(postUrl);
		model.addAttribute("postByUrl", postDto);
		return "/admin/view_posts";
		
	}
	
	//Handler Method for Search Request
	@GetMapping("/admin/posts/search")
	public String searchRequest(@RequestParam(value="query") String query, 
								Model model) {
		List<PostDto> posts = postService.searchPost(query);
		model.addAttribute("posts", posts);
		return "admin/posts";
		
	}
	
	//Handler Method for List comments in admin view
	@GetMapping("/admin/posts/comments")
	public String getComments(Model model) {

		List<CommentDto> allComments  = null;
		String role = SecurityUtils.getRole();
		if(ROLE.ROLE_ADMIN.name().equals(role)) {
			allComments = commentService.findALLComments();
		}
		else {
			 allComments = commentService.findCommentByUser();
		}
		model.addAttribute("allcomments", allComments);
		return "admin/view_comments";
	}
	
	//Handler Method for Delete Comments
	@GetMapping("/admin/posts/comments/{commentId}")
	public String deleteComments(@PathVariable("commentId") Long commentId) {
		commentService.deleteComment(commentId);
		return "redirect:/admin/posts/comments";
		
	}
	
	private static String getUrl(String postTitle) {
		//OOPS Concepts Explained in Java
		//oops-concepts-explained-in-java
		
		String title = postTitle.trim().toLowerCase();
		String url = title.replaceAll("\\s+", "-");
		System.out.println("URL :"+ url);
		url = url.replaceAll("[^A-Za-z0-9]", "-");
		return postTitle;
	}
}
