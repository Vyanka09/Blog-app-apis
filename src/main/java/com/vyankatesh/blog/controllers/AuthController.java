package com.vyankatesh.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RestController;

import com.vyankatesh.blog.api.AuthApi;
import com.vyankatesh.blog.exceptions.InvalidUsernameOrPassword;
import com.vyankatesh.blog.payloads.JWTAuthRequest;
import com.vyankatesh.blog.payloads.JWTAuthResponse;
import com.vyankatesh.blog.payloads.UserDto;
import com.vyankatesh.blog.security.JwtTokenHelper;
import com.vyankatesh.blog.services.UserService;

@RestController
public class AuthController implements AuthApi {

	
	private JwtTokenHelper jwtTokenHelper;
	
	private UserDetailsService userDetailsService;
	
	private AuthenticationManager manager;
	
	private UserService userService;
	
	@Autowired
	public AuthController(JwtTokenHelper jwtTokenHelper, UserDetailsService userDetailsService,
			AuthenticationManager manager, UserService userService) {
		this.jwtTokenHelper = jwtTokenHelper;
		this.userDetailsService = userDetailsService;
		this.manager = manager;
		this.userService=userService;
	}

	@Override
	public ResponseEntity<JWTAuthResponse> createToken(JWTAuthRequest jwtAuthRequest) {
		// TODO Auto-generated method stub
		
		this.authenticate(jwtAuthRequest.getUsername(),jwtAuthRequest.getPassword());
		UserDetails userDetails=userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());
		  String token = jwtTokenHelper.generateToken(userDetails);
		  JWTAuthResponse response=new JWTAuthResponse();
		  response.setToken(token);
		  
		return new ResponseEntity<JWTAuthResponse>(response,HttpStatus.OK);
	}

	private void authenticate(String email, String password) {
		// TODO Auto-generated method stub

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (Exception e) {
            throw new InvalidUsernameOrPassword(" Invalid Username or Password  !!");
        }
		
	}

	@Override
	public ResponseEntity<UserDto> registerNewUser(UserDto userDto) {
		// TODO Auto-generated method stub
		return new ResponseEntity<UserDto>(userService.registerNewUser(userDto),HttpStatus.CREATED);
	}

}
