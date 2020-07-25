package com.honger1234.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 测试Controller
 * @author: zt
 * @date: 2020年3月26日
 */
@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping("hello")
    public Object test() {
        return "强大的spring Security";
    }

    @RequestMapping("success")
    public Object success() {
        return "成功";
    }

    @RequestMapping("fail")
    public Object fail() {
        return "失败";
    }
}
