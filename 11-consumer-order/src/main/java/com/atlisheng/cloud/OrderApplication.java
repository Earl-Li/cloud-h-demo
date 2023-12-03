package com.atlisheng.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients//激活Feign消费端对服务的调用，OpenFeign默认是开启负载均衡的，默认使用的是Ribbon的轮询负载均衡，如果没有配置负载均衡是不会生效的
//1. 服务提供方心跳设置发送心跳间隔大于心跳等待上限，虽然eureka显示一切正常，但是该服务无法提供服务，展示的效果是服务列表有，但是无法负载均衡
//2. eureka集群没有相互注册【即eureka服务器存在还是以单击版形式启动的服务器】，也无法实现负载均衡
public class OrderApplication {
    public static void main(String[] args){
        SpringApplication.run(OrderApplication.class,args);
    }
}
