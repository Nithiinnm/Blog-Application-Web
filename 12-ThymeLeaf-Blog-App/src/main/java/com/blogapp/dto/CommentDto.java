package com.blogapp.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {

	private Long id;
	
	@NotEmpty(message = "Name should not be empty")
	private String name;
	
	@NotEmpty(message = "email should not be empty")
	@Email
	private String email;
	
	@NotEmpty(message = "Content should not be empty")
	private String content;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime updatedDate;
	
}
