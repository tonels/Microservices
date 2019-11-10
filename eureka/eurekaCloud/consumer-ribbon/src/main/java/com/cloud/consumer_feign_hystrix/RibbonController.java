package com.cloud.consumer_feign_hystrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/consumer")
    public String ribbon() {
        return restTemplate.getForObject("http://eureka-client/client", String.class);
    }

    @GetMapping("/")
    public String test() {
        return "ribbon test success...";
    }

}
