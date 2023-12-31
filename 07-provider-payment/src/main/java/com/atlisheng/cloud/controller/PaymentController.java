package com.atlisheng.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/zk")
    public String paymentRegistryByZk(){
        log.info("slf4j是否正常");
        return "Registry with zookeeper:"+serverPort+"\t"+ UUID.randomUUID().toString();
    }
}
