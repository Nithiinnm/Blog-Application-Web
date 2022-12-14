package com.blogapp.mapper;

import java.util.stream.Collectors;

import com.blogapp.dto.PostDto;
import com.blogapp.entity.Post;

public class PostMapper {
	
	//convert Post to Post_Dto
		public static PostDto mapToPostDto(Post post) {
			return  PostDto.builder().id(post.getId())
						.shortDescription(post.getShortDescription())
						.title(post.getTitle()).url(post.getUrl())
						.content(post.getContent())
						.comments(post.getComments()
									  .stream()
									  .map((comment) -> CommentMapper.mapToCommentDto(comment))
									  .collect(Collectors.toSet()))
						.createdDate(post.getCreatedDate())
						.createdBy(post.getCreatedBy())
						.updateDate(post.getUpdateDate()).build();
			
		}
	
	//convert Post_Dto to Post Entity
	public static Post mapToPost(PostDto postDto) {
		return  Post.builder().id(postDto.getId())
					.shortDescription(postDto.getShortDescription())
					.title(postDto.getTitle()).url(postDto.getUrl())
					.content(postDto.getContent()).createdDate(postDto.getCreatedDate())
					.updateDate(postDto.getUpdateDate()).build();
	}

}
