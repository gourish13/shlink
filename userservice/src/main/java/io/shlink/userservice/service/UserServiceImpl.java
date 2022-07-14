package io.shlink.userservice.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.shlink.userservice.dao.UserRepository;
import io.shlink.userservice.exception.ServiceDownException;
import io.shlink.userservice.model.Url;
import io.shlink.userservice.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UrlService urlService;

	@Override
	public List<User> fetchAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User fetchUserById(Integer id) {
		return userRepository.findById(id).orElseThrow();
	}

	@Override
	public void createUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void updateUserById(Integer id, User updatedUser) {
		User user = userRepository.findById(id).orElseThrow();
		
		if (updatedUser.getEmail() != null)
			user.setEmail(updatedUser.getEmail());
		if (updatedUser.getPassword() != null)
			user.setPassword(updatedUser.getPassword());
		if (updatedUser.getIsAdmin() != null)
			user.setIsAdmin(updatedUser.getIsAdmin());
		
		userRepository.save(user);
	}

	@Override
	public void deleteUserById(Integer id) throws ServiceDownException {
		ResponseEntity<?> response = urlService.deleteAllUrlsForUser(id);
		
		if (response.getStatusCode().equals(HttpStatus.NOT_FOUND))
			throw new ServiceDownException("Url Service is down, "
					+ "cannot delete urls, try again later");

		userRepository.deleteById(id);
	}

	@Override
	public List<Url> fetchAllUrlById(Integer id) {
		Optional<List<Url>> urls = urlService.fetchAllUrlInfoByUserId(id);
		logger.info("Url Info : {}", urls);
		
		if (urls.isEmpty())
			return null;
		return urls.get();
	}

}
