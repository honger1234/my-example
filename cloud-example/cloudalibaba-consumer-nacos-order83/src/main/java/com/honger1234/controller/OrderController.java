package com.honger1234.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author: zt
 * @date: 2020/7/22
 */
@RestController
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serviceUrl;

    @RequestMapping(value = "/consumer/nacos/payment/get/{id}")
    public String getPaymentInfi(@PathVariable("id") Integer id){
        return restTemplate.getForObject(serviceUrl+"/nacos/payment/get/"+id,String.class);
    }
}
