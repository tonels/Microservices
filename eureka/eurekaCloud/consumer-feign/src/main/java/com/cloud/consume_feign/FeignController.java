package com.cloud.consume_feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class FeignController {

    @Resource
    FeClient feClient;

    @GetMapping("/consumer")
    public String dc() {
        return feClient.consumer();
    }

}
