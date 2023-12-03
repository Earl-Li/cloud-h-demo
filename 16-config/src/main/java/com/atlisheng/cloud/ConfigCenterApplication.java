package com.atlisheng.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer//标记该服务是一个服务注册中心
public class ConfigCenterApplication {
    public static void main(String[] args){
        SpringApplication.run(ConfigCenterApplication.class,args);
    }
}
