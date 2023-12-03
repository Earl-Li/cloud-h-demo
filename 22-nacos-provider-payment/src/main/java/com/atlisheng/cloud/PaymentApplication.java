package com.atlisheng.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient//启动类使用@EnableDiscoveryClient启动服务发现客户端功能
@SpringBootApplication
public class PaymentApplication {
    public static void main(String[] args) {
            SpringApplication.run(PaymentApplication.class, args);
    }
}
