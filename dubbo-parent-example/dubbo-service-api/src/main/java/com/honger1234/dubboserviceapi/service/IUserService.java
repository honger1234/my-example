package com.honger1234.dubboserviceapi.service;

import com.honger1234.dubboserviceapi.entity.UserEntity;

/**
 * @Description: 用户接口类
 * @author: zt
 * @Date: 2020/6/17 16:31
 */
public interface IUserService {

    UserEntity getUserInfo(int userId);

}
