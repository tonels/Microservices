package com.cloud.consume_feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("eureka-client")
public interface FeClient {

    @GetMapping("/client")
    String consumer();

}
