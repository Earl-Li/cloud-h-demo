package com.atlisheng.cloud.dao;

import com.atlisheng.cloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Earl
 * @version 1.0.0
 * @描述 原则上各种增删改查的方法都要写上
 * @创建日期 2023/10/20
 * @since 1.0.0
 */
@Mapper
public interface PaymentDao {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
}
