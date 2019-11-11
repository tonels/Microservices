package com.main;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableHystrix
@RestController
public class Hystrix {
    public static void main(String[] args) {
        SpringApplication.run(Hystrix.class, args);
    }

    @RequestMapping(value = "/")
    @HystrixCommand(fallbackMethod = "fallback_hello",
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
            }
    )
    public String hello() throws InterruptedException {
        Thread.sleep(2000);
        return "Welcome Hystrix";
    }
    private String fallback_hello() {
        return "请求失败，响应超时...";
    }
}