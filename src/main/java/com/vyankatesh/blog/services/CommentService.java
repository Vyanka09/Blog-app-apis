package com.vyankatesh.blog.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.vyankatesh.blog.payloads.CommentRequestDto;
import com.vyankatesh.blog.payloads.CommentResponseDto;

public interface CommentService {

	//Create comment
	public CommentResponseDto createComment(CommentRequestDto commentRequestDto,Integer userId,Integer postId);
	
	//Update comment
	public CommentResponseDto updateComment(CommentRequestDto commentRequestDto,Integer commentId);
	
	//Delete comment
	public ResponseEntity<String> deleteComment(Integer commentId);
	
	//Get All comments of Specific Post
	public List<CommentResponseDto> getAllCommentsOfPost(Integer postId);
}
