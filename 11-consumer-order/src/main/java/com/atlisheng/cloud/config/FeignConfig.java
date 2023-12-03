package com.atlisheng.cloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel(){
        //开启feign的详细日志，显示请求和响应的头信息，正文以及元数据
        return Logger.Level.FULL;
    }
}
