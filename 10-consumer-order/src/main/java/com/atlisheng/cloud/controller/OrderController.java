package com.atlisheng.cloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
    //Zookeeper的服务名严格区分大小写，Eureka不区分
    public static final String PAYMENT_URL="http://consul-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    /**
     * @return {@link String }
     * @描述 consumer注册到Zookeeper中，通过Zookeeper调用payment服务返回结果
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/10/25
     * @since 1.0.0
     */
    @GetMapping("/consumer/payment/consul")
    public String paymentInvokeByZk(){
        String result=restTemplate.getForObject(PAYMENT_URL+"/payment/consul",String.class);
        return result;
    }
}
