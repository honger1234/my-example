package com.honger1234.service.impl;

import com.honger1234.dao.IUserDao;
import com.honger1234.dubboserviceapi.entity.UserEntity;
import com.honger1234.dubboserviceapi.service.IUserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:dubbo的测试服务
 * @author: zt
 * @Date: 2020/6/17 16:15
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    public UserEntity getUserInfo(int userId){
        return userDao.getUserInfo(userId);
    }
}
