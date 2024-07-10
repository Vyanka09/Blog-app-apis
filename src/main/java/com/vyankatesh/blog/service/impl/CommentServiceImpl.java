package com.vyankatesh.blog.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vyankatesh.blog.entity.Comments;
import com.vyankatesh.blog.entity.Post;
import com.vyankatesh.blog.entity.User;
import com.vyankatesh.blog.exceptions.CommentNotFound;
import com.vyankatesh.blog.exceptions.PostNotFound;
import com.vyankatesh.blog.exceptions.UserNotFound;
import com.vyankatesh.blog.payloads.CommentRequestDto;
import com.vyankatesh.blog.payloads.CommentResponseDto;
import com.vyankatesh.blog.repository.CommentsRepository;
import com.vyankatesh.blog.repository.PostRepository;
import com.vyankatesh.blog.repository.UserRepository;
import com.vyankatesh.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	private CommentsRepository commentRepository;
	
	private ModelMapper modelMapper;
	
	private UserRepository userRepository;
	
	private PostRepository postRepository;
	
	@Autowired
	public CommentServiceImpl(CommentsRepository commentRepository, ModelMapper modelMapper,
			UserRepository userRepository, PostRepository postRepository) {
		this.commentRepository = commentRepository;
		this.modelMapper = modelMapper;
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}

	@Override
	public CommentResponseDto createComment(CommentRequestDto commentRequestDto, Integer userId, Integer postId) {
		// TODO Auto-generated method stub
		User user=userRepository.findById(userId).orElseThrow(()->new UserNotFound("User","Id",userId));
		Post post=postRepository.findById(postId).orElseThrow(()->new PostNotFound("Post","Id",postId));
		Comments comment= modelMapper.map(commentRequestDto, Comments.class);
		comment.setPost(post);
		comment.setUser(user);
		commentRepository.save(comment);
		return modelMapper.map(comment, CommentResponseDto.class);
	}

	@Override
	public CommentResponseDto updateComment(CommentRequestDto commentRequestDto, Integer commentId) {
		// TODO Auto-generated method stub
		Comments comment=commentRepository.findById(commentId).orElseThrow(()->new CommentNotFound("Comment","Id",commentId));
		comment.setContent(commentRequestDto.getContent());
		commentRepository.save(comment);
		return modelMapper.map(comment, CommentResponseDto.class);
		
	}

	@Override
	public ResponseEntity<String> deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		Comments comment=commentRepository.findById(commentId).orElseThrow(()->new CommentNotFound("Comment","Id",commentId));
		commentRepository.delete(comment);
		return new ResponseEntity<String>("Comment deleted successfully with id "+commentId,HttpStatus.OK);
	}

	@Override
	public List<CommentResponseDto> getAllCommentsOfPost(Integer postId) {
		// TODO Auto-generated method stub
		Post post=postRepository.findById(postId).orElseThrow(()->new PostNotFound("Post","Id",postId));
		List<Comments> commentsList=commentRepository.findByPostPostId(postId);
		return commentsList.stream().map(comment->modelMapper.map(comment,CommentResponseDto.class)).toList();
		
	}

}
