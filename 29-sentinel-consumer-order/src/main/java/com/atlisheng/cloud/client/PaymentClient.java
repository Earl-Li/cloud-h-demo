package com.atlisheng.cloud.client;

import com.atlisheng.cloud.client.impl.PaymentFallback;
import com.atlisheng.cloud.entities.Payment;
import com.atlisheng.cloud.resp.CommonResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Earl
 * @version 1.0.0
 * @描述 使用 fallback 方式是无法获取异常信息的,如果想要获取异常信息，可以使用 fallbackFactory参数
 * @创建日期 2023/11/22
 * @since 1.0.0
 */
@FeignClient(value = "sentinel-payment-provider", fallback = PaymentFallback.class)//调用中关闭9003服务提供者
public interface PaymentClient {
    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResp<Payment> paymentSQL(@PathVariable("id") Long id);
}