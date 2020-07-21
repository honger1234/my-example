package com.honger1234.controller;

import com.honger1234.entities.CommonResult;
import com.honger1234.entities.Payment;
import com.honger1234.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author: zt
 * @date: 2020年3月26日
 */
@RestController
@Slf4j
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment( @PathVariable("id") long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public Object paymentFeignTimeout(){
        return paymentFeignService.paymentFeignTimeout();
    }

}
