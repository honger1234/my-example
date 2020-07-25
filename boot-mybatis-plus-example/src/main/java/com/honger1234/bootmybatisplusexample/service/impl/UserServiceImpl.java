package com.honger1234.bootmybatisplusexample.service.impl;

import com.honger1234.bootmybatisplusexample.entity.UserEntity;
import com.honger1234.bootmybatisplusexample.dao.IUserDao;
import com.honger1234.bootmybatisplusexample.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zt
 * @since 2020-05-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<IUserDao, UserEntity> implements IUserService {

}
