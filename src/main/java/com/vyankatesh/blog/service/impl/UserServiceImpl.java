package com.vyankatesh.blog.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vyankatesh.blog.config.AppConstants;
import com.vyankatesh.blog.entity.Role;
import com.vyankatesh.blog.entity.User;
import com.vyankatesh.blog.exceptions.UserNotFound;
import com.vyankatesh.blog.payloads.UserDto;
import com.vyankatesh.blog.repository.RoleRepository;
import com.vyankatesh.blog.repository.UserRepository;
import com.vyankatesh.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public UserDto addUser(UserDto user) {
		// TODO Auto-generated method stub
		User savedUser=userDtoToUser(user);
		userRepository.save(savedUser);
		return userToUserDto(savedUser);
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<User>userList=userRepository.findAll();
		List<UserDto>userDtoList=userList.stream().map(user->userToUserDto(user)).collect(Collectors.toList());
		return userDtoList;
	}

	@Override
	public UserDto getUserById(int id) {
		// TODO Auto-generated method stub
		Optional<User>user=userRepository.findById(id);
		if(user.isPresent())
		{
			return userToUserDto(user.get());
		}
		 throw new UserNotFound("User","Id",id);
	}

	@Override
	public UserDto updateUser(int id, UserDto user) {
		// TODO Auto-generated method stub
		Optional<User>existingUser=userRepository.findById(id);
		if(existingUser.isPresent())
		{
			User updatedUser=existingUser.get();
			updatedUser=userDtoToUser(user);
			updatedUser.setId(id);
			userRepository.save(updatedUser);
			return userToUserDto(updatedUser);
		}
		throw new UserNotFound("User","Id",id);
	}

	@Override
	public ResponseEntity<String> deleteUser(int id) {
		// TODO Auto-generated method stub
		Optional<User>user=userRepository.findById(id);
		if(user.isPresent())
		{
		   userRepository.delete(user.get());
		   return new ResponseEntity<String>("User with id "+id+" deleted successfully", HttpStatus.OK);
		}
		throw new UserNotFound("User","Id",id);
	}
	
	public User userDtoToUser(UserDto userDto)
	{
//		return User.builder().id(userDto.getId()).about(userDto.getAbout()).email(userDto.getEmail()) 
//				.name(userDto.getName()).password(userDto.getPassword()).build();
		
		return modelMapper.map(userDto, User.class);
	}
	
	public UserDto userToUserDto(User user)
	{
//		return UserDto.builder().id(user.getId()).name(user.getName()).email(user.getEmail())
//				.about(user.getAbout()).password(user.getPassword()).build();
		
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto registerNewUser(UserDto userdto) {
		// TODO Auto-generated method stub
		User user=modelMapper.map(userdto, User.class);
		
		//Encoded the passwords
		user.setPassword(passwordEncoder.encode(userdto.getPassword()));
		
		//Role
		Role role=roleRepository.findById(AppConstants.normalUser).get();
		
		Set<Role> roles= user.getRoles();
		roles.add(role);
		User newUser=userRepository.save(user);
		
		return modelMapper.map(newUser, UserDto.class);
	}

}
