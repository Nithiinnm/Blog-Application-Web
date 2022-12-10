package com.blogapp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.blogapp.dto.CommentDto;
import com.blogapp.entity.Comment;
import com.blogapp.entity.Post;
import com.blogapp.entity.User;
import com.blogapp.mapper.CommentMapper;
import com.blogapp.repository.CommentRepository;
import com.blogapp.repository.PostRepository;
import com.blogapp.repository.UserRepository;
import com.blogapp.utils.SecurityUtils;

@Service
public class CommentServiceImpl implements CommentService {
	
	private CommentRepository commentRepo;
	
	private PostRepository postRepo;
	
	private UserRepository userRepo;
	
	public CommentServiceImpl(CommentRepository commentRepo, PostRepository postRepo, UserRepository userRepo) {
		this.commentRepo = commentRepo;
		this.postRepo = postRepo;
		this.userRepo = userRepo;
	}

	@Override
	public void createComment(String postURL, CommentDto comment) {
		
	 Post post = postRepo.findByUrl(postURL).get();
	 Comment commentObj = CommentMapper.mapToComment(comment);
	 commentObj.setPost(post);
	 commentRepo.save(commentObj);
	 
	}

	@Override
	public List<CommentDto> findALLComments() {
		List<Comment> comments = commentRepo.findAll();
		//return comments.stream().map(CommentMapper :: mapToCommentDto).collect(Collectors.toList());
		return comments.stream().map((comment)->CommentMapper.mapToCommentDto(comment)).collect(Collectors.toList());
	}

	@Override
	public void deleteComment(Long commentId) {
		commentRepo.deleteById(commentId);
		
	}

	@Override
	public List<CommentDto> findCommentByUser() {
		String username = SecurityUtils.getCurrentUser().getUsername();
		User user = userRepo.findByEmail(username);
		Long userId = user.getId();
		List<Comment> comments = commentRepo.findCommentByUser(userId);
		return comments.stream().map((comment) -> CommentMapper.mapToCommentDto(comment)).collect(Collectors.toList()) ;
	}

}
