package com.atlisheng.cloud.controller;

import com.atlisheng.cloud.entities.Payment;
import com.atlisheng.cloud.resp.CommonResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
    public static final String PAYMENT_URL="http://service-payment";

    @Resource
    private RestTemplate restTemplate;

    /**
     * @param payment
     * @return {@link CommonResp }<{@link Payment }>
     * @描述 RestTemplate发送post请求对其他服务进行调用，参数以对象的形式传入
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/10/20
     * @since 1.0.0
     */
    @PostMapping("/consumer/payment/create")
    public CommonResp<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment, CommonResp.class);
    }

    /**
     * @param id
     * @return {@link CommonResp }<{@link Payment }>
     * @描述 RestTemplate发送Get请求对其他服务进行调用，参数直接拼接在url尾部
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/10/20
     * @since 1.0.0
     */
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResp<Payment> getPayment(@PathVariable Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id, CommonResp.class);
    }

    /**
     * @param id
     * @return {@link CommonResp }<{@link Payment }>
     * @描述 只需要json串用Object，需要更详细的信息就需要Entity，现在json串使用量比较大
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/10/26
     * @since 1.0.0
     */
    @GetMapping("/consumer/payment/getEntity/{id}")
    public CommonResp<Payment> getEntity(@PathVariable Long id){
        ResponseEntity<CommonResp> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResp.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            log.info(entity.toString());
            return entity.getBody();
        }else{
            return new CommonResp<>(444,"服务调用异常");
        }
    }


    /**
     * @return {@link String }
     * @描述 zipkin+sleuth监控下的订单服务，其中调用payment中的控制器方法paymentZipkin
     * @author Earl
     * @version 1.0.0
     * @创建日期 2023/11/13
     * @since 1.0.0
     */
    @GetMapping("/consumer/order/zipkin")
    public String paymentZipkin() {
        String result = restTemplate.getForObject(PAYMENT_URL+"/payment/zipkin/", String.class);
        return result;
    }
}
