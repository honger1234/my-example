package com.honger1234;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author: zt
 * @date: 2020/7/8
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableHystrixDashboard
public class HystrixDashboard9001 {
    public static void main(String[] args){
        SpringApplication.run(HystrixDashboard9001.class,args);
    }
}
