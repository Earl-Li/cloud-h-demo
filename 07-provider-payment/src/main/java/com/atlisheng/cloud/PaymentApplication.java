package com.atlisheng.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient//该注解用于向使用consul或者Zookeeper作为注册中心时微服务向注册中心进行注册
public class PaymentApplication {
    public static void main(String[] args){
        SpringApplication.run(PaymentApplication.class,args);
    }
}
