package com.honger1234.service.impl;

import com.honger1234.exception.BusinessException;
import com.honger1234.service.ITestService;
import org.springframework.stereotype.Service;

/**
 * @Description: 业务接口实现类
 * @author: zt
 * @date: 2020年3月26日
 */
@Service
public class TestServiceImpl implements ITestService {

    @Override
    public void insert() {
        String name = null;
        //如果姓名为空就手动抛出一个自定义的异常！
        if (name == null) {
            throw new BusinessException("用户姓名不能为空！");
//            throw  new BizException("-1","用户姓名不能为空！");
        }
    }

    @Override
    public void update() {
        //这里故意造成一个空指针的异常，并且不进行处理
        String str = null;
        str.equals("111");
    }

    @Override
    public void delete() {
        //这里故意造成一个异常，并且不进行处理
        Integer.parseInt("abc123");
    }
}
