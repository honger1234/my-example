package com.honger1234;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: zt
 * @date: 2020/7/22
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerOrder83 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrder83.class,args);
    }
}
