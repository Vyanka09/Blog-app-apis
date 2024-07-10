package com.vyankatesh.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vyankatesh.blog.repository.UserRepository;

@SpringBootTest
class BlogAppApisApplicationTests {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void repo()
	{
		String packageName=userRepository.getClass().getPackageName();
		String className=userRepository.getClass().getName();
		System.out.println(packageName+" "+className);
	}

}
