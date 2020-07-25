package com.honger1234.bootmapperexample.mapper;

import tk.mybatis.mapper.common.base.select.SelectAllMapper;
import tk.mybatis.mapper.common.example.SelectByExampleMapper;

/**
 * @Description: 自定义Mapper
 * @author: zt
 * @date: 2020年3月26日
 */
public interface MyMapper<T> extends SelectAllMapper<T>, SelectByExampleMapper<T>, MyBatchUpdateMapper<T> {

}
