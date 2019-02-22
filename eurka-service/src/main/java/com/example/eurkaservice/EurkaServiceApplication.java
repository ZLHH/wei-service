package com.example.eurkaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurkaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurkaServiceApplication.class, args);
	}
}
