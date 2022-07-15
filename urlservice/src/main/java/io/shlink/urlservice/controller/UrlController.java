package io.shlink.urlservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.shlink.urlservice.model.Url;

@RequestMapping("/urls")
public interface UrlController {
	
	@GetMapping("")
	ResponseEntity<List<Url>> fetchAllUrlInfo();
	
	@GetMapping("/{code}")
	ResponseEntity<Url> fetchUrlInfoByCode(
			@PathVariable(name = "code") String code);
	
	@PostMapping("/new")
	ResponseEntity<String> createNewShortUrl(@RequestBody Url url);
	
	@PutMapping("/update/{code}")
	ResponseEntity<String> updateLongUrlByCode(
			@PathVariable String code, @RequestBody Url updatedUrl);
	
	@DeleteMapping("/delete/{code}")
	ResponseEntity<String> deleteUrlInfoByCode(@PathVariable String Code);
	
	@RequestMapping(path = "/for/user/{userId}", method = RequestMethod.GET)
	List<Url> fetchAllUrlWithUserId(@PathVariable Integer userId);
	
	@RequestMapping(path = "/delete/for/user/{userId}", method = RequestMethod.DELETE)
	ResponseEntity<String> deleteAllUrlsWithUserId(@PathVariable Integer userId);
	
	@DeleteMapping("/delete/expired")
	ResponseEntity<?> deleteAllExpiredUrl();

}
