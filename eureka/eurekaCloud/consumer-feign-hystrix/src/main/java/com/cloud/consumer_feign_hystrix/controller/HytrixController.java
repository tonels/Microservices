package com.cloud.consumer_feign_hystrix.controller;

import com.alibaba.fastjson.JSONObject;
import com.cloud.consumer_feign_hystrix.feignClient.FeiClient;
import com.cloud.consumer_feign_hystrix.hystrix.FeiCustHysCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class HytrixController {

    @Resource
    FeiClient feiClient;

    @GetMapping("/consumer")
    public String feignHytrix() {
        return feiClient.consumer();
    }


    // HystrixCommand 方式拦截断路
    @GetMapping("/hystrix")
    public String Hytrix() {
        JSONObject execute = new FeiCustHysCommand(feiClient).execute();
        System.out.println(execute.toJSONString());
        return execute.toJSONString();
    }


    // feignHystrix 方式拦截断路
    @GetMapping("/feignHystrix")
    public String feignHystrix() {
        return feiClient.consumer();
    }

}
