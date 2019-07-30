package com.ruiger.springcloud.eureka_client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/")
public class TestController {

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping(value = "/test")
    public String test(HttpServletRequest request) throws InterruptedException {
//        Thread.sleep(5000l);
        String services = "Services: " + discoveryClient.getServices();
        logger.info("====<call client, TraceId={}, SpanId={}>====",request.getHeader("X-B3-TraceId"), request.getHeader("X-B3-SpanId"));

        return services;
    }
}
