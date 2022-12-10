package com.blogapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.blogapp.dto.CommentDto;
import com.blogapp.dto.PostDto;
import com.blogapp.service.CommentService;
import com.blogapp.service.PostService;

@Controller
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private PostService postService;
	
	//Needs to add Logger
	
	//Handler Method for Save Comments
	@PostMapping("/{postUrl}/comments")
	public String saveComment(@PathVariable("postUrl") String postUrl, 
							  @Valid @ModelAttribute("comment") CommentDto commentDto,
							  BindingResult result,
							  Model model) {
		
		PostDto postDto = postService.findPostByUrl(postUrl);
		
		if(result.hasErrors()) {
			model.addAttribute("post", postDto);
			model.addAttribute("comment", commentDto);
			return "blog/blog_view";
		}
		commentService.createComment(postUrl, commentDto);
		return "redirect:/post/"+postUrl;
		
	}
	

}
