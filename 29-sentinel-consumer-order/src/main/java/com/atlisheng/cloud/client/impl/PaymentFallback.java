package com.atlisheng.cloud.client.impl;

import com.atlisheng.cloud.client.PaymentClient;
import com.atlisheng.cloud.entities.Payment;
import com.atlisheng.cloud.resp.CommonResp;
import org.springframework.stereotype.Component;

//@Component
public class PaymentFallback implements PaymentClient {
    @Override
    public CommonResp<Payment> paymentSQL(Long id) {
        return new CommonResp<>(444, "服务降级返回,没有该流水信息", new Payment(id, "errorSerial......"));
    }
}