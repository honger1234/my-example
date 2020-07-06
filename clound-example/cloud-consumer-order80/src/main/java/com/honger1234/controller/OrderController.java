package com.honger1234.controller;

import com.honger1234.entities.CommonResult;
import com.honger1234.entities.Payment;
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
public class OrderController {

//    public static final String url="http://localhost:8001";
    public static final String url="http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("consumer/create")
    public CommonResult create(@RequestBody Payment payment){
        return restTemplate.postForObject(url+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("consumer/payment/get/{id}")
    public CommonResult getPayment( @PathVariable("id") long id){
        return restTemplate.getForObject(url+"/payment/get/"+id,CommonResult.class);
    }

}
