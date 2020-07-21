package com.honger1234.controller;

import cn.hutool.core.lang.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 控制层
 * @author: zt
 * @date: 2020年3月26日
 */
@RestController
@RequestMapping("payment")
public class PaymentController {

    @Value("${server.port}")
    private int port;

    @RequestMapping(value = "/zk")
    public Object paymentZk(){
        return "springCloud with zookeeper"+port+"\t"+ UUID.randomUUID();
    }
}
