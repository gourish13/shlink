package io.shlink.urlservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UrlServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlServiceApplication.class, args);
	}

}
