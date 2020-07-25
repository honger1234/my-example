package com.honger1234.dao;

import com.honger1234.dubboserviceapi.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 用户持久层
 * @author: zt
 * @Date: 2020/6/18 12:02
 */
@Mapper
public interface IUserDao {

    UserEntity getUserInfo(int userId);

}
