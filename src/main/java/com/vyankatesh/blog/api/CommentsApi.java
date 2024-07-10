package com.vyankatesh.blog.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vyankatesh.blog.payloads.CommentRequestDto;
import com.vyankatesh.blog.payloads.CommentResponseDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping("/api/comments")
public interface CommentsApi {

	@PostMapping("")
	@ApiOperation("API for adding commentt")
	public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto commentRequestDto
			,@RequestParam Integer userId,@RequestParam Integer postId);
	
	@PutMapping("/{commentId}")
	@ApiOperation("API for updating commentt")
	public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Integer commentId, @RequestBody 
			CommentRequestDto commentRequestDto);
	
	@DeleteMapping("/{commentId}")
	@ApiOperation("API for deleting comment")
	public ResponseEntity<String> deleteComment(@PathVariable Integer commentId);
	
	@GetMapping("/post/{postId}")
	@ApiOperation("API for getting comments on a single post")
	public ResponseEntity<List<CommentResponseDto>> getCommentsOnPost(@PathVariable Integer postId);
}
