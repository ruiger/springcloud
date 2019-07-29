package com.ruiger.springcloud.eureka_consumer.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ruiger.springcloud.eureka_consumer.service.ConsumerService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Resource
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    @Override
    public String consumer() {
        return restTemplate.getForObject("http://localhost:3001/test", String.class);
    }

    public String fallback(){
        return "fallback";
    }
}
