package io.shlink.urlservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class UrlServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlServiceApplication.class, args);
	}

}
