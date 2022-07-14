package io.shlink.userservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import io.shlink.userservice.UserServiceApplication;
import io.shlink.userservice.dao.UserRepository;
import io.shlink.userservice.model.User;

@SpringBootTest
@ContextConfiguration(classes = {
		UserServiceImpl.class,
		UrlService.class,
		UserServiceApplication.class
})
class UserServiceImplTest {
	
	@MockBean
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	
	@Test
	final void createUserTest() {
		Mockito.when(userRepository
				.save(Mockito.any(User.class)))
				.thenAnswer(i -> {
					System.out.println("Creation:\n" + i.getArguments()[0]);
					return i.getArguments()[0];
				});
		
		userService.createUser(new User(1, "asdf@gmail.com", "1231eewd", true));
	}
	
	@Test
	final void fetchAllUserTest() {
		List<User> users = new ArrayList<>();
		users.add(new User(1, "asdf@gmail.com", "1231eewd", true));
		users.add(new User(2, "qwerty@gmail.com", "123adfsdeewd", false));

		Mockito.when(userRepository.findAll()).thenReturn(users);
		
		assertThat(userService.fetchAllUser().size()).isEqualTo(2);
	}
	
	@Test
	final void fetchUserByIdTest() {
		List<User> users = Stream.of(
			new User(1, "asdf@gmail.com", "1231eewd", true),
			new User(2, "qwerty@gmail.com", "123adfsdeewd", false))
				.collect(Collectors.toList());
		
		Mockito.when(userRepository
				.findById(Mockito.anyInt()))
				.thenAnswer(i -> {
					User user = users.get((Integer) i.getArgument(0) - 1);
					System.out.println("Fetch By Id:\n" + user);
					return Optional.of(user);
				});
		
		assertThat(userService
				.fetchUserById(1).toString()
				.equals(users.get(0).toString()))
		.isTrue();
		assertThat(userService
				.fetchUserById(2).toString()
				.equals(users.get(1).toString()))
		.isTrue();
	}

	@Test
	final void updateUserById() {
		List<User> users = Arrays.stream(new User[] {
			new User(1, "asdf@gmail.com", "1231eewd", true),
			new User(2, "qwerty@gmail.com", "123adfsdeewd", false)
		}).collect(Collectors.toCollection(ArrayList::new));

		Mockito.when(userRepository
				.save(Mockito.any(User.class)))
				.thenAnswer(i -> {
					User user = i.getArgument(0);
					users.set(user.getId() - 1, user);
					return i.getArguments()[0];
				});
		
		Mockito.when(userRepository
				.findById(Mockito.anyInt()))
				.thenAnswer(i -> {
					User user = users.get((Integer) i.getArgument(0) - 1);
					System.out.println("Update by Id:\n" + user);
					return Optional.of(user);
				});
		
		User updatedUser =  new User(1, "qwerty@yahoo.com", "123", true);
		
		userService.updateUserById(1, updatedUser);
		assertThat(userService
				.fetchUserById(1).toString()
				.equals(updatedUser.toString()))
		.isTrue();
	}
	
}