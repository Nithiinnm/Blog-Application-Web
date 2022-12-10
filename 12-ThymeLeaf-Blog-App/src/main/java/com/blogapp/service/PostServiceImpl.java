package com.blogapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.blogapp.dto.PostDto;
import com.blogapp.entity.Post;
import com.blogapp.entity.User;
import com.blogapp.mapper.PostMapper;
import com.blogapp.repository.PostRepository;
import com.blogapp.repository.UserRepository;
import com.blogapp.utils.SecurityUtils;

@Service
public class PostServiceImpl implements PostService {
	
	//@Autowired -> not required because spring 4.3 if the class contians one constructor it will inject dependency automatically
	private PostRepository postRepository;
	
	private UserRepository userRepo;
	
	public PostServiceImpl(PostRepository postRepository,UserRepository userRepository) {
		this.postRepository = postRepository;
		this.userRepo = userRepository;
	}

	@Override
	public List<PostDto> findAllPosts() {
		List<Post> posts = postRepository.findAll();
		//return posts.stream().map((post) -> PostMapper.mapToPostDto(post)).collect(Collectors.toList());
		return posts.stream().map(PostMapper :: mapToPostDto).collect(Collectors.toList());
	}

	@Override
	public void createPost(PostDto postDto) {
		String email = SecurityUtils.getCurrentUser().getUsername();
		User user = userRepo.findByEmail(email);
		Post post = PostMapper.mapToPost(postDto);
		post.setCreatedBy(user);
		postRepository.save(post);
	}

	@Override
	public PostDto findByPostId(Long postId) {
		Post post = postRepository.findById(postId).get();
		return PostMapper.mapToPostDto(post);
	}

	@Override
	public void updatePost(PostDto post) {
		String email = SecurityUtils.getCurrentUser().getUsername();
		User user = userRepo.findByEmail(email);
		Post updatePost = PostMapper.mapToPost(post);
		updatePost.setCreatedBy(user);
		postRepository.save(updatePost);
	}

	@Override
	public void deletePostById(Long postId) {
		postRepository.deleteById(postId);
	}

	@Override
	public PostDto findPostByUrl(String postUrl) {
		Post post = postRepository.findByUrl(postUrl).get();
		return PostMapper.mapToPostDto(post);
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> searchPost = postRepository.searchPostHQL(keyword);
		return searchPost.stream()
						 .map(PostMapper :: mapToPostDto)
						 .collect(Collectors.toList());
	}

	@Override
	public List<PostDto> findPostByUser() {
		String email = SecurityUtils.getCurrentUser().getUsername();
		User user = userRepo.findByEmail(email);
		Long UserId = user.getId();
		List<Post> post = postRepository.findPostByUser(UserId);
		return post.stream().map((posts) ->PostMapper.mapToPostDto(posts)).collect(Collectors.toList());
	}
}
