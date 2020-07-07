package com.honger1234.config.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author: zt
 * @date: 2020/7/7
 */
@RestController
@RequestMapping(value = "consumer")
public class OrderZkController {

    private static final String url="http://cloud-payment-service";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("payment/zk")
    public Object paymentInfo(){
        return restTemplate.getForObject(url + "/payment/zk", String.class);
    }
}
