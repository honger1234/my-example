package com.honger1234.orderserviceconsumer.controller;

import com.honger1234.dubboserviceapi.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 订单controller层
 * @author: zt
 * @Date: 2020/6/18 14:00
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/getOrderInfo")
    public Object getOrderInfo(int userId,String orderId){
        Object orderInfo = orderService.getOrderInfo(userId, orderId);
        return orderInfo;
    }

    @RequestMapping("/test")
    public  Object test(){
        return "测试";
    }
}
