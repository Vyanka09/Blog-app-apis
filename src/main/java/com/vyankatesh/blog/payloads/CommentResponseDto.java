package com.vyankatesh.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {
	
	private Integer id;
	private String content;
	private PostResponseDto post;
	private UserDto user;
}

