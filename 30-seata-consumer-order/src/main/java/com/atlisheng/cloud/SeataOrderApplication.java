package com.atlisheng.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//取消数据源的自动配置，谁管理数据源，谁就能控制事务。默认druid管理，应该交由seata管理。seata管理数据源后，seata才能管理事务。
public class SeataOrderApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(SeataOrderApplication.class, args);
    }
}