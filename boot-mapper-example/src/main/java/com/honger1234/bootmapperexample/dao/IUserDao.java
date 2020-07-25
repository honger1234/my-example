package com.honger1234.bootmapperexample.dao;

import com.honger1234.bootmapperexample.entity.UserEntity;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Description: 用户持久层接口
 * @author: zt
 * @date: 2020年3月26日
 */

public interface IUserDao extends Mapper<UserEntity> {
}
