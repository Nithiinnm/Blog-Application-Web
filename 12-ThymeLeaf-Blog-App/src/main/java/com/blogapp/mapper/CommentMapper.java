package com.blogapp.mapper;

import com.blogapp.dto.CommentDto;
import com.blogapp.entity.Comment;

public class CommentMapper {
	
	//convert Comment to Comment_Dto
			public static CommentDto mapToCommentDto(Comment comment) {
				return  CommentDto.builder()
							.id(comment.getId())
							.name(comment.getName())
							.email(comment.getEmail())
							.content(comment.getContent())
							.createdDate(comment.getCreatedDate())
							.updatedDate(comment.getUpdatedDate())
							.build();
			}
		
		//convert Comment_Dto to Comment
		public static Comment mapToComment(CommentDto commentDto) {
			return  Comment.builder()
						.id(commentDto.getId())
						.name(commentDto.getName())
						.email(commentDto.getEmail())
						.content(commentDto.getContent())
						.createdDate(commentDto.getCreatedDate())
						.updatedDate(commentDto.getUpdatedDate())
						.build();
		}

}
