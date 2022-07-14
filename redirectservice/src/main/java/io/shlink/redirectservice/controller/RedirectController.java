package io.shlink.redirectservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.shlink.redirectservice.service.RedirectService;

@RestController
@RequestMapping("/redirect")
public class RedirectController {
	
	@Autowired
	RedirectService redirectService;
	
	@GetMapping("/{urlCode}")
	public ResponseEntity<String> redirectToLongUrl(
			@PathVariable String urlCode, 
			@RequestParam(
					required = false, 
					name = "fake") 
			Boolean fake) {
		
		String url = redirectService.getLongUrlForCode(urlCode);
		
		if (url.startsWith("Not Found:"))
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(url);
		
		if (fake != null && fake == true)
			return ResponseEntity
					.ok("Long Url: " + url);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", url);
		return new ResponseEntity<String>(headers, HttpStatus.FOUND);
	}

}
