package com.cloud.consumer_feign_hystrix;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "eureka-client", fallback = FeignClientFallback.class)
public interface FeiClient {

    @GetMapping("/client")
    String consumer();

}
