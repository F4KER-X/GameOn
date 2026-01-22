package com.game.on.go_team_service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DiscoveryDebugLogger implements ApplicationRunner {

    private final DiscoveryClient discoveryClient;

    @Override
    public void run(ApplicationArguments args) {
        List<ServiceInstance> instances = discoveryClient.getInstances("go-user-service");
        log.info("🔍 Eureka instances for go-user-service: size={}", instances.size());
        instances.forEach(i ->
                log.info("🔍 Instance: host={}, port={}, secure={}, uri={}",
                        i.getHost(), i.getPort(), i.isSecure(), i.getUri())
        );
    }
}
