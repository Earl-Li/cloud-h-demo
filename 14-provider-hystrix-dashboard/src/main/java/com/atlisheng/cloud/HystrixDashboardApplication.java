package com.atlisheng.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard//使用@EnableHystrixDashboard注解开启hystrix dashboard功能
public class HystrixDashboardApplication {
    public static void main(String[] args){
        SpringApplication.run(HystrixDashboardApplication.class,args);
    }
}
