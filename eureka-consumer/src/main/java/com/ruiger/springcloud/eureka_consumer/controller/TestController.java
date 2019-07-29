package com.ruiger.springcloud.eureka_consumer.controller;

import com.ruiger.springcloud.eureka_consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Resource
    RestTemplate restTemplate;

    @Resource
    private ConsumerService consumerService;

    @GetMapping("/consumer")
    public String test() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/test";
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/consumerHystrix")
    public String testHystrix() {
       return consumerService.consumer();
    }
}
