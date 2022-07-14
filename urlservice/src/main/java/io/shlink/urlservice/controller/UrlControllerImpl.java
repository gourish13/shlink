package io.shlink.urlservice.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.shlink.urlservice.model.Url;
import io.shlink.urlservice.service.UrlService;

@RestController
public class UrlControllerImpl implements UrlController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UrlService urlService;

	@Override
	public ResponseEntity<List<Url>> fetchAllUrlInfo() {
		List<Url> urls = urlService.fetchAllUrls();
		return ResponseEntity.ok(urls);
	}

	@Override
	public ResponseEntity<Url> fetchUrlInfoByCode(String code) {
		Url url;
		try {
			url = urlService.fetchUrlInfoByCode(code);
		} catch (NoSuchElementException nse) {
			return ResponseEntity.ok(null);
		}
		
		return ResponseEntity.ok(url);
	}

	@Override
	public ResponseEntity<String> createNewShortUrl(Url url) {
		String urlCode = urlService.createNewShortUrl(url);

		logger.info("Short Url created with Url Code {}", urlCode);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body("Short Url created with Url Code " + urlCode);
	}

	@Override
	public ResponseEntity<String> updateLongUrlByCode(String code, Url updatedUrl) {
		Url url;
		try {
			url = urlService.updateLongUrl(code, updatedUrl);
		} catch (NoSuchElementException nse) {
			return ResponseEntity
					.ok("Cannot update. Either unmatched url code or url already expired");
		}
		
		logger.info("Updated url code {} with long url {}", code, url.getLongUrl());
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body("Successfully updated long url");
	}

	@Override
	public ResponseEntity<String> deleteUrlInfoByCode(String code) {
		urlService.deleteUrlByCode(code);
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.body(null);
	}

	@Override
	public ResponseEntity<String> deleteAllUrlsWithUserId(Integer userId) {
		urlService.deleteAllForUser(userId);
		
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

	@Override
	public List<Url> fetchAllUrlWithUserId(Integer userId) {
		return urlService.fetchAllUrlsForUser(userId);
	}

}
