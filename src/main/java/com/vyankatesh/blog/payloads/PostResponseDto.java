package com.vyankatesh.blog.payloads;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {

	private Integer postId;
	private String title;
	private String imageName;
	
	private String content;
	private Date addedDate;
	private CategoryDto category;
	private UserDto user;
}
