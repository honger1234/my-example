package com.honger1234;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description: 主启动
 * @author: zt
 * @date: 2020年3月26日
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Payment8004 {
    public static void main(String[] args){
        SpringApplication.run(Payment8004.class,args);
    }
}
