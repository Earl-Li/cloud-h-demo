package com.atlisheng.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @auther zzyy
 * @create 2020-02-10 12:01
 */
@EnableDiscoveryClient
@SpringBootApplication
public class OrderApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(OrderApplication.class,args);
    }
} 