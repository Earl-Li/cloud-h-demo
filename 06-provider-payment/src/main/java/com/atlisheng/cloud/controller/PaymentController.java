package com.atlisheng.cloud.controller;

import com.atlisheng.cloud.entities.Payment;
import com.atlisheng.cloud.resp.CommonResp;
import com.atlisheng.cloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j//用于日志打印，自动注入log对象
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;

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

    @GetMapping("/payment/services")
    public Object getServices(){
        List<String> services = discoveryClient.getServices();
        for (String service:services) {
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            String serviceList=new String();
            for (ServiceInstance instance:instances) {
                serviceList=serviceList+
                        instance.getServiceId()+"\t"+
                        instance.getInstanceId()+"\t"+
                        instance.getHost()+"\t"+
                        instance.getPort()+"\t"+
                        instance.getUri()+"\t"+
                        instance.getScheme()+"\t"+
                        instance.getMetadata()+"\t"+
                        instance.getClass();
            }
            log.info("serviceName:"+service+"|serviceList:"+serviceList);
        }
        return this.discoveryClient;
    }

}
