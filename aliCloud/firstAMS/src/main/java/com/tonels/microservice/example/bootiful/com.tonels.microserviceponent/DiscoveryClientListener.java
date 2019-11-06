package com.tonels.microservice.example.bootiful.com.tonels.microserviceponent;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class DiscoveryClientListener {

    private final DiscoveryClient discoveryClient;

    public DiscoveryClientListener(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void useDiscoveryClient() {
        this.discoveryClient
                .getServices()
                .forEach(log::info);
    }
}
