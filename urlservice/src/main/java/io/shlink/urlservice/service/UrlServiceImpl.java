package io.shlink.urlservice.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;

import io.shlink.urlservice.configprops.UrlExpiryConfigurationProperty;
import io.shlink.urlservice.model.Url;
import io.shlink.urlservice.repository.UrlRepository;

@Service
public class UrlServiceImpl implements UrlService {
	
	final int URLCODE_LENGTH = 21;
	
	@Autowired
	private UrlRepository urlRepository;
	@Autowired
	private UrlExpiryConfigurationProperty urlExpiryConfigurationProperty;

	@Override
	public List<Url> fetchAllUrls() {
		return urlRepository.findAll();
	}

	@Override
	public Url fetchUrlInfoByCode(String code) {
		String urlCode = code.substring(0, URLCODE_LENGTH);
		Long id = Long.valueOf(code.substring(URLCODE_LENGTH));

		Timestamp expectedCreatedAt = new 
				Timestamp(System.currentTimeMillis()
						- urlExpiryConfigurationProperty.getMilliSeconds());
		
		return urlRepository
				.findByIdAndUrlCodeAndCreatedAtAfter(id, urlCode, expectedCreatedAt)
				.orElseThrow();
	}

	@Override
	public String createNewShortUrl(Url url) {
		String urlCode = NanoIdUtils.randomNanoId();
		Timestamp createdAt = new Timestamp(System.currentTimeMillis());
		
		url.setUrlCode(urlCode);
		url.setCreatedAt(createdAt);
		
		urlRepository.save(url);
		
		return urlCode + url.getId();
	}
	
	@Override
	public Url updateLongUrl(String code, Url updatedUrl) {
		String urlCode = code.substring(0, URLCODE_LENGTH);
		Long id = Long.valueOf(code.substring(URLCODE_LENGTH));

		Timestamp expectedCreatedAt = new 
				Timestamp(System.currentTimeMillis()
						- urlExpiryConfigurationProperty.getMilliSeconds());
		
		Url url = urlRepository
				.findByIdAndUrlCodeAndCreatedAtAfter(id, urlCode, expectedCreatedAt)
				.orElseThrow();
		
		if (updatedUrl.getLongUrl() != null)
			url.setLongUrl(updatedUrl.getLongUrl());
		
		urlRepository.save(url);
		return url;
	}

	@Override
	public void deleteUrlByCode(String code) {
		String urlCode = code.substring(0, URLCODE_LENGTH);
		Long id = Long.valueOf(code.substring(URLCODE_LENGTH));
		
		urlRepository.deleteByIdAndUrlCode(id, urlCode);
	}

	@Override
	public void deleteAllForUser(Integer userId) {
		urlRepository.deleteAllByUserId(userId);
	}

	@Override
	public List<Url> fetchAllUrlsForUser(Integer userId) {
		return urlRepository.findAllByUserId(userId);
	}

}