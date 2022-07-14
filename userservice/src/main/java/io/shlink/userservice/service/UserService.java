package io.shlink.userservice.service;

import java.util.List;

import io.shlink.userservice.exception.ServiceDownException;
import io.shlink.userservice.model.Url;
import io.shlink.userservice.model.User;

public interface UserService {

	List<User> fetchAllUser();
	User fetchUserById(Integer id);
	List<Url> fetchAllUrlById(Integer id); 
	void createUser(User user);
	void updateUserById(Integer id, User updatedUser);
	void deleteUserById(Integer id) throws ServiceDownException;

}