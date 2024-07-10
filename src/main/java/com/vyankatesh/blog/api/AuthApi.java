package com.vyankatesh.blog.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vyankatesh.blog.payloads.JWTAuthRequest;
import com.vyankatesh.blog.payloads.JWTAuthResponse;
import com.vyankatesh.blog.payloads.UserDto;

@RequestMapping("/api/v1/auth")
public interface AuthApi {

	@PostMapping("/login")
	public ResponseEntity<JWTAuthResponse> createToken(@RequestBody JWTAuthRequest jwtAuthRequest);
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerNewUser(@RequestBody UserDto userDto);
}
