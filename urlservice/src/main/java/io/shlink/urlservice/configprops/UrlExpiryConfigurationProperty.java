package io.shlink.urlservice.configprops;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "urls.expiry")
public class UrlExpiryConfigurationProperty {
	
	private Integer seconds;

	public Integer getSeconds() {
		return seconds;
	}

	public void setSeconds(Integer seconds) {
		this.seconds = seconds;
	}
	
	public Long getMilliSeconds() {
		return seconds * 1000L;
	}

}