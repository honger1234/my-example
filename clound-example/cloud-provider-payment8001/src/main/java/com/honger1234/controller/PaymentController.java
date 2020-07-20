package com.honger1234.controller;

import com.honger1234.entities.CommonResult;
import com.honger1234.entities.Payment;
import com.honger1234.service.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 控制层
 * @author: zt
 * @date: 2020年3月26日
 */
@RestController
@Slf4j
@RequestMapping("payment")
public class PaymentController {

    @Value("${server.port}")
    private int port;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private IPaymentService paymentService;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        int i = paymentService.create(payment);
        log.info("*****插入结果："+i);
        if (i<1){
            return new CommonResult(444,"插入失败serverPort:"+port,null);
        }
        return new CommonResult(200,"插入成功serverPort:"+port,i);
    }

    @GetMapping("/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") long id){
        Payment payment = paymentService.getPaymentById(id);
        if (payment==null){
            return new CommonResult(444,"没有查到数据serverPort:"+port,payment);
        }
        return new CommonResult(200,"查询成功serverPort:"+port,payment);
    }

    @GetMapping(value = "/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String s:services){
            log.info("服务名称："+s);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance serviceInstance:instances){
            log.info(serviceInstance.getServiceId()+"\t"+serviceInstance.getHost()+"\t"+serviceInstance.getPort()+"\t"+serviceInstance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/feign/timeout")
    public Object paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return port;
    }
}
