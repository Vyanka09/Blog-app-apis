package com.vyankatesh.blog.exceptions;


public class PostNotFound extends RuntimeException {


	private String resourceName;
	private String fieldName;
	private int fieldValue;
	public PostNotFound(String resourceName, String fieldName, int fieldValue) {
		super(resourceName+ " not foud with "+fieldName+": "+fieldValue);
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
}
