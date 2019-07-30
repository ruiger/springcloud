package com.ruiger.springcloud.eureka_consumer.controller;

import com.ruiger.springcloud.eureka_consumer.service.ConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {
    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Resource
    RestTemplate restTemplate;

    @Resource
    private ConsumerService consumerService;

    @GetMapping("/consumer")
    public String test(HttpServletRequest request) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/test";
        logger.info("====<call consumer, TraceId={}, SpanId={}>====",request.getHeader("X-B3-TraceId"), request.getHeader("X-B3-SpanId"));
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/consumerHystrix")
    public String testHystrix() {
       return consumerService.consumer();
    }
}
