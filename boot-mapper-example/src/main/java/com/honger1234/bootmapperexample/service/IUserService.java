package com.honger1234.bootmapperexample.service;

import com.honger1234.bootmapperexample.entity.UserEntity;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Description: 业务层接口类
 * @author: zt
 * @date: 2020年3月26日
 */
public interface IUserService {
    /**
     * 查询一条数据
     * @param user
     * @return
     */
    UserEntity getSelectOne(UserEntity user);

    /**
     * t通过主键查询用户信息
     * @return
     * @param userId
     */
    UserEntity getUserById(int userId);

    /**
     * 插入一条数据
     * @param userEntity
     * @return
     */
    UserEntity save(UserEntity userEntity);

    /**
     * 插入数据
     * @param userEntity
     * @return
     */
    UserEntity saveUserSelective(UserEntity userEntity);

    /**
     * 更新数据
     * @param userEntity
     * @return
     */
    UserEntity updateByPrimaryKeySelective(UserEntity userEntity);

    /**
     * 通过主键删除数据
     * @param userId
     * @return
     */
    boolean deleteUserById(int userId);

    /**
     * QBC查询
     * @return
     * @param example
     */
    List<UserEntity> getUserListByExample(Example example);
}
