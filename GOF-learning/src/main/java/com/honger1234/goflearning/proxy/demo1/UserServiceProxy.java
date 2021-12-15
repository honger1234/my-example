package com.honger1234.goflearning.proxy.demo1;

public class UserServiceProxy implements UserService{
    private UserService userService;
    public UserServiceProxy(UserService userService){
        this.userService=userService;
    }
    @Override
    public void add() {
        System.out.println("新增日志");
        userService.add();
    }

    @Override
    public void delete() {
        System.out.println("删除日志");
        userService.delete();
    }

    @Override
    public void update() {
        System.out.println("修改日志");
        userService.update();
    }

    @Override
    public void list() {
        System.out.println("查询日志");
        userService.delete();
    }
}
