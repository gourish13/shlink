package io.shlink.userservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import io.shlink.userservice.controller.UserController;
import io.shlink.userservice.dao.UserRepository;
import io.shlink.userservice.service.UserService;

@SpringBootTest
class UserServiceApplicationTests {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private UserController userController;
	@Autowired
	private RestTemplate restTemplate;

	@Test
	void contextLoads() {
	}
	
	@Test
	void autowiredTest() {
		assertThat(userRepository).isNotNull();
		assertThat(userService).isNotNull();
		assertThat(userController).isNotNull();
		assertThat(restTemplate).isNotNull();
	}

}
