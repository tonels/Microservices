package com.cloud.consumer_feign_hystrix;

import org.springframework.stereotype.Component;

@Component
public class FeignClientFallback implements FeiClient {

    @Override
    public String consumer() {
        return "请求超时...";
    }
}
