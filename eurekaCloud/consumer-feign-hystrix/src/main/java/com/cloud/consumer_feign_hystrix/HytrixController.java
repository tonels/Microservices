package com.cloud.consumer_feign_hystrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HytrixController {

    @Qualifier("feignClientFallback")
    @Autowired
    FeiClient feiClient;

    @GetMapping("/consumer")
    public String feignHytrix() {
        return feiClient.consumer();
    }

}
