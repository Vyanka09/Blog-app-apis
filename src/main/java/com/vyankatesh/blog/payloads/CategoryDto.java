package com.vyankatesh.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

	private Integer categoryId;
	
	@NotEmpty
	@Size(min=4,message="Name should be of at least 4 characters.")
	private String categoryName;
	
	@NotEmpty
	@Size(min=15,max=100, message="Description should be between 15 to 100 characters.")
	private String categoryDescription;
}
