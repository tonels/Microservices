package com.cloud.consul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/server")
    public String dc() {

        List<String> services1 = discoveryClient.getServices();

        String services = "consul服务上已注册: " + services1;
        System.out.println(services);
        return services;
    }

}
