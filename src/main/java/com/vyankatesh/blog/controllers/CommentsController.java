package com.vyankatesh.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.vyankatesh.blog.api.CommentsApi;
import com.vyankatesh.blog.payloads.CommentRequestDto;
import com.vyankatesh.blog.payloads.CommentResponseDto;
import com.vyankatesh.blog.services.CommentService;

@RestController
public class CommentsController implements CommentsApi {

	private CommentService commentService;
	
	@Autowired
	public CommentsController(CommentService commentService) {
		this.commentService = commentService;
	}


	@Override
	public ResponseEntity<CommentResponseDto> createComment(CommentRequestDto commentRequestDto, Integer userId,
			Integer postId) {
		// TODO Auto-generated method stub
		return new ResponseEntity<CommentResponseDto>(commentService.createComment(commentRequestDto, userId, postId),HttpStatus.OK);
	}


	@Override
	public ResponseEntity<CommentResponseDto> updateComment(Integer commentId, CommentRequestDto commentRequestDto) {
		// TODO Auto-generated method stub
		return new ResponseEntity<CommentResponseDto>(commentService.updateComment(commentRequestDto, commentId),
				HttpStatus.OK);
	}


	@Override
	public ResponseEntity<String> deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		return commentService.deleteComment(commentId);
	}


	@Override
	public ResponseEntity<List<CommentResponseDto>> getCommentsOnPost(Integer postId) {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<CommentResponseDto>>(commentService.getAllCommentsOfPost(postId),HttpStatus.OK);
	}

	

}
