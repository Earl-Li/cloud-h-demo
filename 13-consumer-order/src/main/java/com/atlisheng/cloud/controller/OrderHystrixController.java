package com.atlisheng.cloud.controller;

import com.atlisheng.cloud.client.PaymentHystrixClient;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "orderGlobalFallback")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixClient paymentHystrixClient;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfoOk( @PathVariable("id") Integer id){
        return paymentHystrixClient.paymentInfoOk(id);
    }
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    })
    /*@HystrixCommand*/
    public String paymentInfoTimeout( @PathVariable("id") Integer id){
        //int age=10/0;
        return paymentHystrixClient.paymentInfoTimeout(id);
    }
    public String paymentInfoTimeoutHandler( @PathVariable("id") Integer id){
        return "支付服务器噶了，完全没响应，老弟半小时后再来吧或者自己运行出错";
    }

    /**
     * @return {@link String }
     * @描述 全局的服务降级fallback
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/10/30
     * @since 1.0.0
     */
    public String orderGlobalFallback(){
        return "全局服务降级及异常处理信息，请稍后再尝试";
    }

}
