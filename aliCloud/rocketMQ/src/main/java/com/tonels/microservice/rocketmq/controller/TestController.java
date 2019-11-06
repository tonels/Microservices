package com.tonels.microservice.rocketmq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/demo/test")
public class TestController {

    @GetMapping("/send")
    public String send() {
        return "success";
    }
}
