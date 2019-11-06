package com.cloud.consul.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "customer")
public class CustomerConfig {
    private String name;
    private String sex;
    private String location;
}
