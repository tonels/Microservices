package com.cloud.consumer_feign_hystrix.feignClient;

import org.springframework.stereotype.Component;

@Component
public class CustoHiHystric implements FeiClient {

    @Override
    public String consumer() {

        return "请求超时";
    }

    @Override
    public String notExist() {
        return null;
    }
}
