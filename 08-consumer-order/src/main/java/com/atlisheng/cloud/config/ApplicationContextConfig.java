package com.atlisheng.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    /**
     * @return {@link RestTemplate }
     * @描述 注入RestTemplate对象
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/10/20
     * @since 1.0.0
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
