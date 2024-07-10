package com.vyankatesh.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vyankatesh.blog.config.AppConstants;
import com.vyankatesh.blog.entity.Role;
import com.vyankatesh.blog.repository.RoleRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BlogAppApisApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(passwordEncoder.encode("xyz"));

		Role role1 = new Role();
		role1.setId(AppConstants.normalUser);
		role1.setName("ROLE_NORMAL");

		Role role2 = new Role();
		role2.setId(AppConstants.adminUser);
		role2.setName("ROLE_ADMIN");

		List<Role> roles = List.of(role1, role2);
		List<Role> result = roleRepository.saveAll(roles);

		result.forEach(r -> {
			System.out.println(r.getName());
		});
	}

}
