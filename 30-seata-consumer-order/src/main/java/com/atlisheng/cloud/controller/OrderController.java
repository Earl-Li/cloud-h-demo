package com.atlisheng.cloud.controller;

import com.atlisheng.cloud.entities.Order;
import com.atlisheng.cloud.resp.CommonResp;
import com.atlisheng.cloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     */
    @GetMapping("/order/create")
    public CommonResp create(Order order) {
        orderService.create(order);
        return new CommonResp(200, "订单创建成功!");
    }
}
