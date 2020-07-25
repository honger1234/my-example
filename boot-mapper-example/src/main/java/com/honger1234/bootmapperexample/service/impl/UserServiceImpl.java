package com.honger1234.bootmapperexample.service.impl;

import com.honger1234.bootmapperexample.dao.IUserDao;
import com.honger1234.bootmapperexample.entity.UserEntity;
import com.honger1234.bootmapperexample.service.IUserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 业务实现类
 * @author: zt
 * @date: 2020年3月26日
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;
    /**
     * 查询一条数据
     *
     * @param user
     * @return
     */
    @Override
    public UserEntity getSelectOne(UserEntity user) {
        return userDao.selectOne(user);
    }

    /**
     * t通过主键查询用户信息
     *
     * @return
     * @param userId
     */
    @Override
    public UserEntity getUserById(int userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    /**
     * 插入一条数据
     *
     * @param userEntity
     * @return
     */
    @Override
    public UserEntity save(UserEntity userEntity) {
        if (userDao.insert(userEntity)>0){
            return userEntity;
        }
        return null;
    }

    /**
     * 插入数据
     *
     * @param userEntity
     * @return
     */
    @Override
    public UserEntity saveUserSelective(UserEntity userEntity) {
        int i = userDao.insertSelective(userEntity);
        if (i>0){
            return userEntity;
        }
        return null;
    }

    /**
     * 更新数据
     *
     * @param userEntity
     * @return
     */
    @Override
    public UserEntity updateByPrimaryKeySelective(UserEntity userEntity) {
        int i = userDao.updateByPrimaryKeySelective(userEntity);
        if (i>0){
            return getUserById(userEntity.getUserId());
        }
        return null;
    }

    /**
     * 通过主键删除数据
     *
     * @param userId
     * @return
     */
    @Override
    public boolean deleteUserById(int userId) {
        int i = userDao.deleteByPrimaryKey(userId);
        if (i>0){
            return true;
        }
        return false;
    }

    /**
     * QBC查询
     *
     * @return
     * @param example
     */
    @Override
    public List<UserEntity> getUserListByExample(Example example) {
        return userDao.selectByExample(example);
    }
}
