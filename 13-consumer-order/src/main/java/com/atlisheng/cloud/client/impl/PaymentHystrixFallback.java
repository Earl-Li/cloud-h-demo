package com.atlisheng.cloud.client.impl;

import com.atlisheng.cloud.client.PaymentHystrixClient;
import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixFallback implements PaymentHystrixClient {
    @Override
    public String paymentInfoOk(Integer id) {
        return "ok方法服务调用服务降级";
    }

    @Override
    public String paymentInfoTimeout(Integer id) {
        return "timeout方法服务调用服务降级";
    }
}
