package com.vyankatesh.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vyankatesh.blog.entity.Category;
import com.vyankatesh.blog.entity.Post;
import com.vyankatesh.blog.entity.User;
import com.vyankatesh.blog.exceptions.CategoryNotFound;
import com.vyankatesh.blog.exceptions.PostNotFound;
import com.vyankatesh.blog.exceptions.UserNotFound;
import com.vyankatesh.blog.payloads.PostRequestDto;
import com.vyankatesh.blog.payloads.PostResponseDto;
import com.vyankatesh.blog.payloads.PostResponsePagination;
import com.vyankatesh.blog.repository.CategoryRepository;
import com.vyankatesh.blog.repository.PostRepository;
import com.vyankatesh.blog.repository.UserRepository;
import com.vyankatesh.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	

	private UserRepository userRepository;
	
	
	private CategoryRepository categoryRepository;
	

	private ModelMapper modelMapper;
	
	
	private PostRepository postRepository;
	

	@Autowired
	public PostServiceImpl(UserRepository userRepository, CategoryRepository categoryRepository,
			ModelMapper modelMapper, PostRepository postRepository) {
		this.userRepository = userRepository;
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
		this.postRepository = postRepository;
	}
	
	@Override
	public PostResponseDto createPost(PostRequestDto postdto, Integer userId, Integer categoryId) {
		// TODO Auto-generated method stub
		User user=userRepository.findById(userId).orElseThrow(()->new UserNotFound("User","Id",userId));
		Category category= categoryRepository.findById(categoryId).orElseThrow(()->new CategoryNotFound("Category"
				,"Id",categoryId));
		Post post=modelMapper.map(postdto,Post.class);
		post.setAddedDate(new Date());
		post.setImageName(postdto.getImageName());
		post.setUser(user);
		post.setCategory(category);
		postRepository.save(post);
		
		return modelMapper.map(post, PostResponseDto.class);
	}

	

	@Override
	public List<PostResponseDto> getAllPostsOfUser(Integer userId) {
		// TODO Auto-generated method stub
		User user=userRepository.findById(userId).orElseThrow(()->new UserNotFound("User","Id",userId));
		List<Post>postList=postRepository.findByUser(user);
		return postList.stream().map(post->modelMapper.map(post,PostResponseDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<PostResponseDto> getAllPostsOfCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category=categoryRepository.findById(categoryId).orElseThrow(()->new CategoryNotFound("Category"
				,"Id",categoryId));
		
		List<Post> postList=postRepository.findByCategory(category);
		return postList.stream().map(post->modelMapper.map(post,PostResponseDto.class)).collect(Collectors.toList());
		
	}

	@Override
	public PostResponseDto updatePost(PostRequestDto post, Integer postId) {
		// TODO Auto-generated method stub
		Post existingpost= postRepository.findById(postId).orElseThrow(()->new PostNotFound("Post","Id",postId));
		existingpost.setContent(post.getContent());
		existingpost.setImageName(post.getImageName());
		existingpost.setTitle(post.getTitle());
		Post savedPost=postRepository.save(existingpost);
		return modelMapper.map(savedPost, PostResponseDto.class);
	}

	@Override
	public PostResponsePagination getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortLevel) {
		// TODO Auto-generated method stub
		Sort sort=null;
		if(sortLevel.equalsIgnoreCase("asc"))
		{
			sort=Sort.by(sortBy).ascending();
		}
		else if(sortLevel.equalsIgnoreCase("desc"))
		{
			sort=Sort.by(sortBy).descending();
		}
		Pageable pageable=PageRequest.of(pageNumber, pageSize, sort);
		Page<Post>postPage=postRepository.findAll(pageable);
		List<Post> postList= postPage.getContent();
		List<PostResponseDto> postResponseList=postList.stream().map(post->modelMapper.map(post, PostResponseDto.class)).toList();
		PostResponsePagination postResponse=new PostResponsePagination();
		postResponse.setContent(postResponseList);
		postResponse.setPageNumber(pageNumber);
		postResponse.setPageSize(pageSize);
		postResponse.setTotalElements(postPage.getTotalElements());
		postResponse.setTotalPages(postPage.getTotalPages());
		postResponse.setLastPage(postPage.isLast());
		
		return postResponse;
	}

	@Override
	public PostResponseDto getSinglePost(Integer postId) {
		// TODO Auto-generated method stub
		Post post= postRepository.findById(postId).orElseThrow(()->new PostNotFound("Post","Id",postId));
		return modelMapper.map(post, PostResponseDto.class);
	}

	@Override
	public ResponseEntity<String> deletePost(Integer postId) {
		// TODO Auto-generated method stub
		Post post= postRepository.findById(postId).orElseThrow(()->new PostNotFound("Post","Id",postId));
		postRepository.delete(post);
		return new ResponseEntity<String>("Post deleted successfully with id "+postId, HttpStatus.OK);
	}

	@Override
	public List<PostResponseDto> getPostsByTitle(String title) {
		// TODO Auto-generated method stub
		List<Post> postList= postRepository.findByTitle("%"+title+"%");
		return postList.stream().map(post->modelMapper.map(post,PostResponseDto.class)).collect(Collectors.toList());
	}

}
