package com.honger1234.controller;

import com.honger1234.service.ITestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @Description: 异常测试类
 * @author: zt
 * @date: 2020年3月26日
 */
@Controller
@RequestMapping("test")
public class TestController {

    @Resource
    private ITestService testService;


    @PostMapping("save")
    public boolean insert(String name) {
        testService.insert();
        return true;
    }

    @PutMapping("update")
    public boolean update() {
        System.out.println("开始更新...");
        testService.update();
        return true;
    }

    @DeleteMapping("delete")
    public boolean delete() {
        System.out.println("开始删除...");
        testService.delete();
        return true;
    }

    @RequestMapping("view")
    public Object view() {
        return "hello";
    }
}


