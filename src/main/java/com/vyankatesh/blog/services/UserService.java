package com.vyankatesh.blog.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.vyankatesh.blog.payloads.UserDto;

public interface UserService {

	UserDto registerNewUser(UserDto userdto);
	UserDto addUser(UserDto user);
	List<UserDto> getAllUsers();
	UserDto getUserById(int id);
	UserDto updateUser(int id, UserDto user);
	ResponseEntity<String> deleteUser(int id);
} 
