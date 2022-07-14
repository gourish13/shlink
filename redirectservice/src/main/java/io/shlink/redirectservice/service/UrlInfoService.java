package io.shlink.redirectservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.shlink.redirectservice.model.Url;

@Service
public class UrlInfoService {

	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getFallbackUrlInfoWithCode",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "6"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "6000")
			}
	)
	Optional<Url> getUrlInfoWithCode(String urlCode) {
		ResponseEntity<Url> response = restTemplate
				.getForEntity("http://url-service/urls/" + urlCode, Url.class);

		return Optional.ofNullable(response.getBody());
	}

	Optional<Url> getFallbackUrlInfoWithCode(String urlCode) {
		Url url = new Url();
		return Optional.of(url);
	}

}
