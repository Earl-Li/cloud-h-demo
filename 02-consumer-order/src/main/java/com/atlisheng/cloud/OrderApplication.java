package com.atlisheng.cloud;

import com.atlisheng.lbrule.CustomizationRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
//在服务调用客户端通过调用服务和指定配置类配置ribbon负载均衡规则
@RibbonClient(name="service-payment",configuration = CustomizationRule.class)
public class OrderApplication {
    public static void main(String[] args){
        SpringApplication.run(OrderApplication.class,args);
    }
}
