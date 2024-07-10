package com.vyankatesh.blog.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.vyankatesh.blog.payloads.PostRequestDto;
import com.vyankatesh.blog.payloads.PostResponseDto;
import com.vyankatesh.blog.payloads.PostResponsePagination;

public interface PostService {

	//Create Post
	PostResponseDto createPost(PostRequestDto post, Integer userId, Integer categoryId);
	
	//Update Post
	PostResponseDto updatePost(PostRequestDto post,Integer postId);
	
	//Get All Posts
	PostResponsePagination getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortLevel);
	
	//Get Single Post
	PostResponseDto getSinglePost(Integer postId);
	
	//Delete post
	ResponseEntity<String> deletePost(Integer postId);
	
	//Get All Posts of Specific User
	List<PostResponseDto> getAllPostsOfUser(Integer userId);
	
	//Get All Posts of Specific Category
	List<PostResponseDto> getAllPostsOfCategory(Integer categoryId);
	
	List<PostResponseDto> getPostsByTitle(String title);
}
