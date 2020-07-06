package com.honger1234.controller;

import com.honger1234.entities.CommonResult;
import com.honger1234.entities.Payment;
import com.honger1234.service.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 控制层
 * @author: zt
 * @date: 2020年3月26日
 */
@RestController
@Slf4j
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        int i = paymentService.create(payment);
        log.info("*****插入结果："+i);
        if (i<1){
            return new CommonResult(444,"插入失败",null);
        }
        return new CommonResult(200,"插入成功",i);
    }

    @GetMapping("/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") long id){
        Payment payment = paymentService.getPaymentById(id);
        if (payment==null){
            return new CommonResult(444,"没有查到数据",payment);
        }
        return new CommonResult(200,"查询成功",payment);
    }
}
