package com.honger1234.controller;

import com.honger1234.dao.IUserDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @author: zt
 * @Date: 2020/6/18 15:19
 */
@RestController
public class TestController {

    @Resource
    private IUserDao userDao;

    @RequestMapping("test")
    public Object test(int userId){
        return userDao.getUserInfo(userId);
    }
}
