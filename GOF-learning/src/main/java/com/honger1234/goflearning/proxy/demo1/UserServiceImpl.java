package com.honger1234.goflearning.proxy.demo1;

public class UserServiceImpl implements UserService{
    @Override
    public void add() {
        System.out.println("新增");
    }

    @Override
    public void delete() {
        System.out.println("删除");
    }

    @Override
    public void update() {
        System.out.println("修改");
    }

    @Override
    public void list() {
        System.out.println("查询");
    }
}
