package com.blogapp.dto;

import java.time.LocalDateTime;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

	private Long id;
	@NotEmpty(message = "Title should not be empty")
	private String title;
	private String url;
	@NotEmpty(message = "Content should not be empty")
	private String content;
	@NotEmpty(message = "ShortDescription should not be empty")
	private String shortDescription;
	private LocalDateTime createdDate;
	private LocalDateTime updateDate;
	private Set<CommentDto> comments;
}
