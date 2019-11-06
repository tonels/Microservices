package com.tonels.microservice.bootiful.ams1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Ams1Application {

    public static void main(String[] args) {
        SpringApplication.run(Ams1Application.class, args);
    }
}

