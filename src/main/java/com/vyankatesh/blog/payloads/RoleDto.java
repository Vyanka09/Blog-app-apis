package com.vyankatesh.blog.payloads;

import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import com.vyankatesh.blog.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class RoleDto {
	
	@Id
	private Integer id;
	
	@NotEmpty
	private String name;

}
