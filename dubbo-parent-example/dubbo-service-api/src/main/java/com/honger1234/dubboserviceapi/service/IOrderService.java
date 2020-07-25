package com.honger1234.dubboserviceapi.service;

/**
 * @Description:
 * @author: zt
 * @Date: 2020/6/18 14:03
 */
public interface IOrderService {
    /**
     * 获取订单信息
     * @param userId
     * @return
     */
    Object getOrderInfo(int userId,String orderId);
}
