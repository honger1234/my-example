package com.honger1234.goflearning.proxy.demo2;


/**
 * 真实角色
 */
public class Landlord implements Rent {
    @Override
    public void rend() {
        System.out.println("房东要出租房子");
    }
}
