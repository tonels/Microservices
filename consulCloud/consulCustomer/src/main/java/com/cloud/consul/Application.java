package com.cloud.consul;

import com.cloud.consul.config.CustomerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties({CustomerConfig.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
