package com.vyankatesh.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.vyankatesh.blog.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserDto {
	private Integer id;
	
	@NotEmpty
	@Size(min=3,message="Name should be of at least 3 characters.")
	private String name;
	
	@NotEmpty
	private String about;
	
	@Email(message="Email must be valid!")
	private String email;
	
	@NotEmpty
	@Size(min=3,max=10,message="Password should be of at least 3 and max 10 characters.")
	private String password;
	
	private Set<RoleDto> roles=new HashSet<>();
}
