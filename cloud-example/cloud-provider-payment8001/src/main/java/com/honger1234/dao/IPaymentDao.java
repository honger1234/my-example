package com.honger1234.dao;

import com.honger1234.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 支付持久层接口
 * @author: zt
 * @date: 2020年3月26日
 */
@Mapper
public interface IPaymentDao {

    int create(Payment payment);

    Payment getPaymentById(long id);
}
