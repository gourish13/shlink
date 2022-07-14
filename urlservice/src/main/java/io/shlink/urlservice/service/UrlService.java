package io.shlink.urlservice.service;

import java.util.List;

import io.shlink.urlservice.model.Url;

public interface UrlService {
	
	List<Url> fetchAllUrls();
	Url fetchUrlInfoByCode(String code);
	String createNewShortUrl(Url url);
	List<Url> fetchAllUrlsForUser(Integer userId);
	Url updateLongUrl(String code, Url updatedUrl);
	void deleteUrlByCode(String code);
	void deleteAllForUser(Integer userId);

}
