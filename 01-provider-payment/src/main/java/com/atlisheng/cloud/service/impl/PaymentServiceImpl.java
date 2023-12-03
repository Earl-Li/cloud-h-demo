package com.atlisheng.cloud.service.impl;

import com.atlisheng.cloud.dao.PaymentDao;
import com.atlisheng.cloud.entities.Payment;
import com.atlisheng.cloud.service.PaymentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    /**
     *Resource注解是java自带的，@Autowire是Spring自带的，使用@Resource也能实现自动注入
     * 由于@Mapper注解不是Spring自带的，使用Autowire注解Spring会认为不能注入从而标红，但是实际运行没有问题，
     * 在Dao上用@Component注解使用@Autowire就不会显示异常
     */
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(@Param("id") Long id){
        return paymentDao.getPaymentById(id);
    }


}
