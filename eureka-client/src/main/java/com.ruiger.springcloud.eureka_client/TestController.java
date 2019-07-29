package com.ruiger.springcloud.eureka_client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class TestController {

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping(value = "/test")
    public String test() throws InterruptedException {
//        Thread.sleep(5000l);
        String services = "Services: " + discoveryClient.getServices();
        return services;
    }
}
