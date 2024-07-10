package com.vyankatesh.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotFound extends RuntimeException {

	private String resouceName;
	private String fieldName;
	private long fieldValue;
	private String fieldValue2;


	public UserNotFound(String resouceName, String fieldName, long fieldValue) {
		super(resouceName+ " not foud with "+fieldName+": "+fieldValue);
		this.resouceName = resouceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}



	public UserNotFound(String resouceName, String fieldName, String fieldValue2) {
		// TODO Auto-generated constructor stub
		super(resouceName+ " not foud with "+fieldName+": "+fieldValue2);
		this.resouceName = resouceName;
		this.fieldName = fieldName;
		this.fieldValue2 = fieldValue2;
	}
}
