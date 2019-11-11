package com.hy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HystrixStudentStart {

	public static void main(String[] args) {
		SpringApplication.run(HystrixStudentStart.class, args);
	}
}
