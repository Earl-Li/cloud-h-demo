package com.atlisheng.cloud.service;

import com.atlisheng.cloud.entities.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);
}