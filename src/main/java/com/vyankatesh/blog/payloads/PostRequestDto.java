package com.vyankatesh.blog.payloads;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {

	private Integer postId;
	@NotEmpty
	private String title;
	@NotEmpty
	private String content;
	private String imageName;
}
