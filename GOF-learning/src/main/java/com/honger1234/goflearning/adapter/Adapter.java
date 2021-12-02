package com.honger1234.goflearning.adapter;
//真正的适配器
public class Adapter extends Adaptee implements NetToUsb{

    @Override
    public void handleRequest() {
        super.request();
    }
}
