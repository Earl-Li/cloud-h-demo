package com.atlisheng.cloud.controller;

import com.atlisheng.cloud.client.PaymentServiceClient;
import com.atlisheng.cloud.resp.CommonResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PaymentController {
    @Autowired//两个注解都能使用
    //@Resource
    PaymentServiceClient paymentServiceClient;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResp order(@PathVariable Long id){
        return paymentServiceClient.getPaymentById(id);
    }

    /**
     * @return {@link String }
     * @描述 openFeign-ribbon客户端一般默认等待时间为1s钟
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/10/28
     * @since 1.0.0
     */
    @GetMapping("/consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        return paymentServiceClient.paymentFeignTimeout();
    }
}
