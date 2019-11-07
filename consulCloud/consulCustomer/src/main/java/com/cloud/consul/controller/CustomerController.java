package com.cloud.consul.controller;

import com.cloud.consul.config.CustomerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class CustomerController {

    @Value("${country}")
    private String country;

    @Resource
    private CustomerConfig customerConfig;

    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Autowired
    RestTemplate restTemplate;


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/consumer")
    public String dc() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("CONSUL-CLIENT");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/server";
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/consul")
    public String s1() {
        ServiceInstance consul = loadBalancerClient.choose("CONSUL");
        System.out.println(consul.getHost());
        System.out.println(consul.getPort());
        return consul.getHost();
    }

    // 测试 Consul Config
    @RequestMapping("/country")
    public String testConfig1() {
        System.out.println("country is : " + country);
        return country;
    }

    @RequestMapping("/config")
    public String testConfig2() {
        System.out.println(customerConfig.toString());
        return customerConfig.toString();
    }


}
