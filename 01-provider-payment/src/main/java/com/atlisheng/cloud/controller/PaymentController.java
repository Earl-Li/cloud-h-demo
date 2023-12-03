package com.atlisheng.cloud.controller;

import com.atlisheng.cloud.entities.Payment;
import com.atlisheng.cloud.resp.CommonResp;
import com.atlisheng.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j//用于日志打印，自动注入log对象
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResp create(@RequestBody Payment payment){
        int result=paymentService.create(payment);
        log.info("插入结果："+result);
        return result>0?new CommonResp(200,"数据插入成功",result):
                new CommonResp(505,"插入数据库失败");
    }

    @GetMapping("/payment/get/{id}")
    public CommonResp getPaymentById(@PathVariable Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果："+payment+"测试动态重启");
        return payment!=null?new CommonResp(200,"查询成功"+serverPort,payment):
                new CommonResp(505,"没有对应ID的记录"+id);
    }

    /**
     * @return {@link String }
     * @描述 模拟服务响应时间大于Feign调用默认超时时长
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/10/28
     * @since 1.0.0
     */
    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }

    /**
     * @return {@link String }
     * @描述 sleuth监控下的payment服务调用
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/11/13
     * @since 1.0.0
     */
    @GetMapping("/payment/zipkin")
    public String paymentZipkin() {
        return "hi ,i'm payment zipkin server fall back O(∩_∩)O哈哈~";
    }

}
