package com.vyankatesh.blog.exceptions;

import java.util.function.Supplier;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryNotFound extends RuntimeException{

	
	private String resourceName;
	private String fieldName;
	private int fieldValue;
	public CategoryNotFound(String resourceName, String fieldName, int fieldValue) {
		super(resourceName+ " not foud with "+fieldName+": "+fieldValue);
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	

	

}
