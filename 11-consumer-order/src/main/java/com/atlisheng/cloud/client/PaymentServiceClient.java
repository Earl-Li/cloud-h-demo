package com.atlisheng.cloud.client;

import com.atlisheng.cloud.resp.CommonResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-payment")
public interface PaymentServiceClient {
    /**
     * @param id
     * @return {@link CommonResp }
     * @描述 参数必须要指定参数名，否则项目无法启动
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/10/28
     * @since 1.0.0
     */
    @GetMapping("/payment/get/{id}")
    public CommonResp getPaymentById(@PathVariable("id") Long id);

    /**
     * @return {@link String }
     * @描述 调用服务模拟服务端响应超时
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/10/28
     * @since 1.0.0
     */
    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout();
}
