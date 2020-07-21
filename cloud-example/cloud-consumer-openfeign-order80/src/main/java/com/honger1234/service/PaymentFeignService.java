package com.honger1234.service;

import com.honger1234.entities.CommonResult;
import com.honger1234.entities.Payment;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: zt
 * @date: 2020/7/7
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping("payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") long id);

    @GetMapping(value = "payment/feign/timeout")
    Object paymentFeignTimeout();
}
