package com.atlisheng.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEurekaClient//不写也能被Eureka注册中心的服务列表显示，但是不知道有没有其他印象
public class ConfigClientApplication {
    public static void main(String[] args){
        SpringApplication.run(ConfigClientApplication.class,args);
    }
}
