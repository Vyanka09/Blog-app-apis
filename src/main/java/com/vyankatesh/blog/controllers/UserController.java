package com.vyankatesh.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vyankatesh.blog.api.UserApi;
import com.vyankatesh.blog.payloads.UserDto;
import com.vyankatesh.blog.services.CategoryService;
import com.vyankatesh.blog.services.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "JWT")
public class UserController implements UserApi {

	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}	
	//Create user - POST
	
	@Override
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto newUser=userService.addUser(userDto);
		return new ResponseEntity<UserDto>(newUser,HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		List<UserDto>allUsers=userService.getAllUsers();
		return new ResponseEntity<List<UserDto>>(allUsers,HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<UserDto> getSingleUser(@PathVariable int userId)
	{
		UserDto user=userService.getUserById(userId);
		return ResponseEntity.ok(user);
	}
	
	@Override
	public ResponseEntity<String> deleteUser(@PathVariable int userId)
	{
		return userService.deleteUser(userId);
	}
	
	@Override
	public ResponseEntity<UserDto> updateUser(@PathVariable int userId,@Valid @RequestBody UserDto userDto)
	{
		UserDto user=userService.updateUser(userId, userDto);
		return ResponseEntity.ok(user);
	}
}
