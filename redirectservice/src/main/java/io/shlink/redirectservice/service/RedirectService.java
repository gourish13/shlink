package io.shlink.redirectservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.shlink.redirectservice.model.Url;

@Service
public class RedirectService {

	@Autowired
	UrlInfoService urlInfoService;
	
	public String getLongUrlForCode(String urlCode) {
		Optional<Url> url = urlInfoService.getUrlInfoWithCode(urlCode);
		
		if (url.isEmpty())
			return "Not Found: Unmatched url code or url expired";
		if (url.get().getLongUrl() == null)
			return "Not Found: Url Service is down, try again later";
		return url.get().getLongUrl();
	}
	
}
