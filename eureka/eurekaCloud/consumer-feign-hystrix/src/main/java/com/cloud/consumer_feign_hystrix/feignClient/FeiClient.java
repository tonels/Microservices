package com.cloud.consumer_feign_hystrix.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "eureka-client",fallback = CustoHiHystric.class )
public interface FeiClient {

    @GetMapping("/client")
    String consumer();

    @GetMapping("notExist")
    String notExist();

}
