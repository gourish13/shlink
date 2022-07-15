package io.shlink.urlservice.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.shlink.urlservice.service.UrlService;

@Aspect
@Component
public class AfterInsertUpdateAspect {

	@Autowired
	private UrlService urlService;
	
	private Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	@Before(value = "execution(public String io.shlink.urlservice.service.UrlServiceImpl.createNewShortUrl(*))")
	public void beforeInsert() {
		urlService.deleteAllExpiredUrls();
		
		logger.info("Deleted expired url before insert");
	}
	
	@Before(value = "execution(public * io.shlink.urlservice.service.UrlServiceImpl.updateLongUrl(String, *))")
	public void beforeUpdate() {
		urlService.deleteAllExpiredUrls();
		
		logger.info("Deleted expired url before update");
	}
	
}
