package com.honger1234.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zt
 * @date: 2020/7/22
 */
@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${config.info}")
    private String info;

    @RequestMapping(value = "/config/info")
    public String getConfigInfo(){
        return "这是配置文件的信息："+info;
    }
}
