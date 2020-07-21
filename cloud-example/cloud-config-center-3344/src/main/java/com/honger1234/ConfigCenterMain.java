package com.honger1234;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author: zt
 * @date: 2020/7/20
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigCenterMain {
    public static void main(String[] args){
        SpringApplication.run(ConfigCenterMain.class,args);
    }
}
