package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@EnableFeignClients(basePackages = "com.app.domain.config")
@SpringBootApplication
public class CalculadoraapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculadoraapiApplication.class, args);
	}

}
