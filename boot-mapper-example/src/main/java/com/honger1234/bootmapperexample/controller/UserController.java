package com.honger1234.bootmapperexample.controller;

import com.honger1234.bootmapperexample.entity.UserEntity;
import com.honger1234.bootmapperexample.service.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @author: zt
 * @date: 2020年3月26日
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private IUserService userService;

    @RequestMapping("selectOne")
    public Object selectOne(){
        UserEntity user=new UserEntity();
        return userService.getSelectOne(user);
    }
}
