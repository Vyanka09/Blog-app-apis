package com.vyankatesh.blog.exception.handling;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vyankatesh.blog.exceptions.CategoryNotFound;
import com.vyankatesh.blog.exceptions.CommentNotFound;
import com.vyankatesh.blog.exceptions.InvalidUsernameOrPassword;
import com.vyankatesh.blog.exceptions.PostNotFound;
import com.vyankatesh.blog.exceptions.UserNotFound;
import com.vyankatesh.blog.payloads.ErrorObject;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<ErrorObject> handleException(UserNotFound uex)
	{
		ErrorObject e=new ErrorObject(uex.getMessage(),HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorObject>(e,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidUsernameOrPassword.class)
	public ResponseEntity<ErrorObject> handleException(InvalidUsernameOrPassword uex)
	{
		ErrorObject e=new ErrorObject(uex.getMessage(),HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ErrorObject>(e,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
	{
		Map<String,String>resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError)error).getField();
			String message=error.getDefaultMessage();
			resp.put(fieldName, message);
		});
		
		
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(CategoryNotFound.class)
	public ResponseEntity<ErrorObject> handleCategoryNotFoundException(CategoryNotFound ex)
	{
		ErrorObject er=new ErrorObject(ex.getMessage(),HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorObject>(er,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PostNotFound.class)
	public ResponseEntity<ErrorObject> handlePostNotFoundException(PostNotFound ex)
	{
		ErrorObject er=new ErrorObject(ex.getMessage(),HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorObject>(er,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CommentNotFound.class)
	public ResponseEntity<ErrorObject> handleCommentNotFoundException(CommentNotFound ex)
	{
		ErrorObject er=new ErrorObject(ex.getMessage(),HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorObject>(er,HttpStatus.NOT_FOUND);
	}
	
}
