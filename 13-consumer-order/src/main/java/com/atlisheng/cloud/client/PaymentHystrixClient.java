package com.atlisheng.cloud.client;

import com.atlisheng.cloud.client.impl.PaymentHystrixFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name="service-hystrix-payment",fallback = PaymentHystrixFallback.class)
public interface PaymentHystrixClient {
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfoOk( @PathVariable("id") Integer id);
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfoTimeout( @PathVariable("id") Integer id);
}
