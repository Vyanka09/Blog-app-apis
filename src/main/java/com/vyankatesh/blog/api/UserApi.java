package com.vyankatesh.blog.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vyankatesh.blog.payloads.UserDto;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/api/users")
public interface UserApi {
	@PostMapping("")
	@ApiOperation(value="Api for adding user")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto);
	
	@GetMapping("")
	@ApiOperation("Api for getting all users")
	public ResponseEntity<List<UserDto>> getAllUsers();
	
	@ApiOperation("Api for getting user by userid")
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable int userId);
	
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation("Api for deleting user")
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable int userId);
	

	@ApiOperation("Api for updating user details")
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@PathVariable int userId,@Valid @RequestBody UserDto userDto);
	
}
