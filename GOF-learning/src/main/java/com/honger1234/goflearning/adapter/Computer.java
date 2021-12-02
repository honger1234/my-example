package com.honger1234.goflearning.adapter;

public class Computer {
    public void net(NetToUsb adapter){
        adapter.handleRequest();
    }

    public static void main(String[] args) {
        Computer computer = new Computer();
        Adaptee adaptee = new Adaptee();//网线
        Adapter adapter = new Adapter();//适配器
        computer.net(adapter);


        Computer computer1 = new Computer();
        Adaptee adaptee1 = new Adaptee();
        Adapter2 adapter2 = new Adapter2(adaptee1);
        computer1.net(adapter2);


    }
}
