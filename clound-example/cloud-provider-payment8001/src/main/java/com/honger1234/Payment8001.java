package com.honger1234;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description: 主启动
 * @author: zt
 * @date: 2020年3月26日
 */
@SpringBootApplication
@EnableEurekaClient
public class Payment8001 {
    public static void main(String[] args){
        SpringApplication.run(Payment8001.class,args);
    }
}
