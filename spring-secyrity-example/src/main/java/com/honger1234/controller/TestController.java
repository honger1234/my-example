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
        return "dev失败";
    }

//    @GetMapping("/login")
//    public AjaxResult login() {
//        AjaxResult ajax = AjaxResult.success();
//        LoginUser loginUser = new LoginUser();
//        AppUser appUser = new AppUser();
//        appUser.setUserId(7L);
//        appUser.setUserName("15603071003");
//        appUser.setPassword("admin123");
//        loginUser.setAppUser(appUser);
//        String token = tokenService.createToken(loginUser, Constants.LOGIN_APP_USER_KEY);
//        ajax.put(Constants.TOKEN, token);
//        return ajax;
//    }
}
