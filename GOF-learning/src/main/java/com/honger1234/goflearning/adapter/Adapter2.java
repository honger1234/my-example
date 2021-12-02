package com.honger1234.goflearning.adapter;
//真正的适配器
public class Adapter2 implements NetToUsb{
    private Adaptee adaptee;
    public Adapter2(Adaptee adaptee){
        this.adaptee=adaptee;
    }

    @Override
    public void handleRequest() {
        adaptee.request();
    }
}
