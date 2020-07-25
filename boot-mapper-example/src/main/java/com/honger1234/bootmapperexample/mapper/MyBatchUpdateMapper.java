package com.honger1234.bootmapperexample.mapper;

import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * @Description: 自定义批量更新
 * @author: zt
 * @date: 2020年3月26日
 */
public interface MyBatchUpdateMapper<T> {

    @UpdateProvider(type= MyBatchUpdateProvider.class, method="dynamicSQL")

    void batchUpdate(List<T> list);

}
