package com.honger1234.goflearning.proxy.demo1;

public class Client {

    public static void main(String[] args) {
        UserService userService=new UserServiceImpl();
        UserServiceProxy userServiceProxy = new UserServiceProxy(userService);

        userServiceProxy.add();
    }
}
