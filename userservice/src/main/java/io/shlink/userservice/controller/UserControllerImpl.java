package io.shlink.userservice.controller;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.shlink.userservice.exception.ServiceDownException;
import io.shlink.userservice.model.Url;
import io.shlink.userservice.model.User;
import io.shlink.userservice.service.UserService;

@RestController
public class UserControllerImpl implements UserController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserService userService;
	
	@Override
	public ResponseEntity<List<User>> fetchAllUsers() {
		List<User> users = userService.fetchAllUser();
		return ResponseEntity.ok(users);
	}

	@Override
	public ResponseEntity<User> fetchUserById(Integer id) {
		User user;
		
		try {
			user = userService.fetchUserById(id);
		} catch (NoSuchElementException nse) {
			return ResponseEntity.ok(null);
		}
		
		return ResponseEntity.ok(user);
	}

	@Override
	public ResponseEntity<String> createNewUser(User user) {
		userService.createUser(user);
		logger.info("Created User with email as {}.", user.getEmail());
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body("Successfully created user.");
	}

	@Override
	public ResponseEntity<String> updateUserById(Integer id, User user) {
		
		try {
			userService.updateUserById(id, user);
		} catch (NoSuchElementException nse) {
			return ResponseEntity
					.ok("Cannot Update, No user with matched id.");
		}
		
		logger.info("Updated user with id {}.", id);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body("Successfully updated user");
	}

	@Override
	public ResponseEntity<String> deleteUserById(Integer id) {
		try {
			userService.deleteUserById(id);
		} catch (ServiceDownException sde) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(sde.getMessage());
		}
		
		logger.info("Deleted user with id {}.", id);

		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.body(null);
	}

	@Override
	public ResponseEntity<? super List<Url>> fetchAllUrlById(Integer id) {
		List<Url> urls = userService.fetchAllUrlById(id);

		if (urls == null)
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Not Found: Url Service is down, try again later");

		return ResponseEntity.ok(urls);
	}

}