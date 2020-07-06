package com.honger1234.service;

import com.honger1234.entities.Payment;

/**
 * @Description: 实现类接口
 * @author: zt
 * @date: 2020年3月26日
 */
public interface IPaymentService {
    int create(Payment payment);

    Payment getPaymentById(long id);
}
