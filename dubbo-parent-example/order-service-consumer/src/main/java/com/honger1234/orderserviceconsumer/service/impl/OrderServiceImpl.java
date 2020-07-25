package com.honger1234.orderserviceconsumer.service.impl;

import com.honger1234.dubboserviceapi.service.IOrderService;
import com.honger1234.dubboserviceapi.service.IUserService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @author: zt
 * @Date: 2020/6/18 14:11
 */
@Service
@Component
public class OrderServiceImpl implements IOrderService {

    @Reference
    private IUserService userService;
    /**
     * 获取订单信息
     *
     * @param userId
     * @param orderId
     * @return
     */
    @Override
    public Object getOrderInfo(int userId, String orderId) {
        return userService.getUserInfo(userId);
    }
}
