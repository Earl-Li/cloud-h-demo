package com.atlisheng.cloud.controller;

import cn.hutool.core.util.IdUtil;
import com.atlisheng.cloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    private int count=0;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfoOk(Integer id) {
        String result = paymentService.paymentInfoOk(id);
        log.info("*****result:"+result+serverPort);
        return result;
    }

    /**
     * @param id
     * @return {@link String }
     * @描述 独立访问payment，并没有通过feign调用，此时正常等待3秒，并不会出现超时的情况
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/10/29
     * @since 1.0.0
     */
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfoTimeout(Integer id) {
        count++;
        String result = paymentService.paymentInfoTimeout(id);
        log.info("*****result:"+result+serverPort+","+count);
        return result+","+count;
    }

    //====服务熔断
    @GetMapping("/payment/hystrix/break/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("result:"+result);
        return result;
    }
}
