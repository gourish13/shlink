package io.shlink.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.shlink.userservice.model.Url;

@Service
public class UrlService {
	
	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "fetchAllUrlInfoByUserIdFallback",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "6000")
			},
			threadPoolKey = "getAllUrlsForUserFromUrlService",
			threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "20"),
					@HystrixProperty(name = "maxQueueSize", value = "10")
			}
	)
	public Optional<List<Url>> fetchAllUrlInfoByUserId(Integer id) {
		ResponseEntity<List<Url>> response = restTemplate.exchange(
					"http://url-service/urls/for/user/" + id,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<List<Url>>() { });
		
		return Optional.of(response.getBody());
	}

	public Optional<List<Url>> fetchAllUrlInfoByUserIdFallback(Integer id) {
		return Optional.empty();
	}

	@HystrixCommand(fallbackMethod = "deleteAllUrlsForUserFallback",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "6000")
			},
			threadPoolKey = "delelteAllUrlsForUserInUrlService",
			threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "20"),
					@HystrixProperty(name = "maxQueueSize", value = "10")
			}
	)
	public ResponseEntity<?> deleteAllUrlsForUser(Integer id) {
		ResponseEntity<?> response = restTemplate.exchange(
					"http://url-service/urls/delete/for/user/" + id,
					HttpMethod.DELETE,
					null,
					ResponseEntity.class
				);
		
		System.out.println(response.getStatusCode());
		return response;
	}

	public ResponseEntity<?> deleteAllUrlsForUserFallback(Integer id) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
