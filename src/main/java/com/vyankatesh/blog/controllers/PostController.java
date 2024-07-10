package com.vyankatesh.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vyankatesh.blog.api.PostApi;
import com.vyankatesh.blog.payloads.PostRequestDto;
import com.vyankatesh.blog.payloads.PostResponseDto;
import com.vyankatesh.blog.payloads.PostResponsePagination;
import com.vyankatesh.blog.services.FileService;
import com.vyankatesh.blog.services.PostService;

@RestController
public class PostController implements PostApi {

	private PostService postService;
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	@Autowired
	public PostController(PostService postService,FileService fileService) {
		
		this.postService = postService;
		this.fileService=fileService;
	}

	@Override
	public ResponseEntity<PostResponseDto> createPost(@RequestParam Integer userId,@RequestParam Integer categoryId,@RequestBody @Valid PostRequestDto postDto)
	{
		PostResponseDto post=postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostResponseDto>(post,HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<List<PostResponseDto>> getPostsByUserId(@PathVariable Integer userId)
	{
		List<PostResponseDto> postList=postService.getAllPostsOfUser(userId);
		return new ResponseEntity<List<PostResponseDto>>(postList,HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<PostResponseDto>> getPostsByCategoryId(@PathVariable Integer categoryId)
	{
		List<PostResponseDto> postList=postService.getAllPostsOfCategory(categoryId);
		return new ResponseEntity<List<PostResponseDto>>(postList,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PostResponsePagination> getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortLevel) {
		// TODO Auto-generated method stub
		return new ResponseEntity<PostResponsePagination>(postService.getAllPosts(pageNumber,pageSize,sortBy,sortLevel),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PostResponseDto> getPostById(@PathVariable Integer postId) {
		// TODO Auto-generated method stub
		PostResponseDto post=postService.getSinglePost(postId);
		return ResponseEntity.ok(post);
	}

	@Override
	public ResponseEntity<PostResponseDto> updatePostById(@PathVariable Integer postId, @RequestBody @Valid PostRequestDto postDto) {
		// TODO Auto-generated method stub
		PostResponseDto post=postService.updatePost(postDto, postId);
		return ResponseEntity.ok(post);
	}

	@Override
	public ResponseEntity<String> deletePostById(@PathVariable Integer postId) {
		return postService.deletePost(postId);
	}

	@Override
	public ResponseEntity<List<PostResponseDto>> getPostByTitle(String keyword) {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<PostResponseDto>>(postService.getPostsByTitle(keyword),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PostResponseDto> uploadImage(Integer postId, MultipartFile image) throws IOException {
		// TODO Auto-generated method stub
		PostResponseDto post=postService.getSinglePost(postId);
		String fileName=fileService.uploadImage(path, image);
		post.setImageName(fileName);
		PostRequestDto postRequestDto=new PostRequestDto();
		postRequestDto.setImageName(fileName);
		postRequestDto.setContent(post.getContent());
		postRequestDto.setTitle(post.getTitle());
		PostResponseDto savedPost=postService.updatePost(postRequestDto, postId);
		
		return new ResponseEntity<PostResponseDto>(savedPost,HttpStatus.OK);
	}

	@Override
	public void downloadImage(String imageName, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		InputStream resource=fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
