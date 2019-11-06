package com.cloud.eurekaClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ClientStart {

    public static void main(String[] args) {
        SpringApplication.run(ClientStart.class);
    }

}
