package io.shlink.userservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.shlink.userservice.model.Url;
import io.shlink.userservice.model.User;

@RequestMapping("/users")
public interface UserController {

	@GetMapping("")
	ResponseEntity<List<User>> fetchAllUsers();
	
	@GetMapping("/{id}")
	ResponseEntity<User> fetchUserById(@PathVariable Integer id);
	
	@PostMapping("/new")
	ResponseEntity<String> createNewUser(@RequestBody User user);
	
	@PutMapping("/update/{id}")
	ResponseEntity<String> updateUserById(@PathVariable Integer id, @RequestBody User user);
	
	@DeleteMapping("/delete/{id}")
	ResponseEntity<String> deleteUserById(@PathVariable Integer id);

	@GetMapping("/{id}/urls")
	ResponseEntity<? super List<Url>> fetchAllUrlById(@PathVariable Integer id);
}
