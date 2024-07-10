package com.vyankatesh.blog.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.vyankatesh.blog.config.AppConstants;
import com.vyankatesh.blog.payloads.PostRequestDto;
import com.vyankatesh.blog.payloads.PostResponseDto;
import com.vyankatesh.blog.payloads.PostResponsePagination;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/api/posts")
public interface PostApi {

	@PostMapping("")
	@ApiOperation("Api for adding post")
	public ResponseEntity<PostResponseDto> createPost(@RequestParam Integer userId, @RequestParam Integer categoryId,
			@RequestBody @Valid PostRequestDto postDto);

	@GetMapping("/user/{userId}")
	@ApiOperation("Api for getting all posts of specific user")
	public ResponseEntity<List<PostResponseDto>> getPostsByUserId(@PathVariable Integer userId);

	@GetMapping("/category/{categoryId}")
	@ApiOperation("Api for getting all posts of specific category")
	public ResponseEntity<List<PostResponseDto>> getPostsByCategoryId(@PathVariable Integer categoryId);

	@GetMapping("")
	@ApiOperation("Api for getting posts by pagination")
	public ResponseEntity<PostResponsePagination> getAllPosts(
			@RequestParam(required = false, defaultValue = AppConstants.pageNumber) Integer pageNumber,
			@RequestParam(required = false, defaultValue = AppConstants.pageSize) Integer pageSize,
			@RequestParam(required = false, defaultValue = AppConstants.sortBy) String sortBy,
			@RequestParam(required = false, defaultValue = AppConstants.sortLevel) String sortLevel);

	@GetMapping("/{postId}")
	@ApiOperation("Api for getting a single post")
	public ResponseEntity<PostResponseDto> getPostById(@PathVariable Integer postId);

	@PutMapping("/{postId}")
	@ApiOperation("Api for updating post details")
	public ResponseEntity<PostResponseDto> updatePostById(@PathVariable Integer postId,
			@RequestBody @Valid PostRequestDto postDto);

	@DeleteMapping("/{postId}")
	@ApiOperation("Api for deleting post ")
	public ResponseEntity<String> deletePostById(@PathVariable Integer postId);
	
	@GetMapping("/search/{keyword}")
	@ApiOperation("Api for getting posts containg specific title")
	public ResponseEntity<List<PostResponseDto>> getPostByTitle(@PathVariable String keyword);
	
	@PostMapping("/{postId}/image/upload")
	@ApiOperation("Api for uploading image")
	public ResponseEntity<PostResponseDto> uploadImage(@PathVariable Integer postId,@RequestParam("image") 
	MultipartFile image) throws IOException;
	
	//Method to serve images
	@GetMapping(value="/image/{imageName}",produces =MediaType.IMAGE_JPEG_VALUE)
	@ApiOperation("Api for getting image")
	public void downloadImage(@PathVariable String imageName,HttpServletResponse response) throws IOException;
}
