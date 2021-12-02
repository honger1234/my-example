package com.honger1234.goflearning.factory.method;

public class Consumer {
    public static void main(String[] args) {
        Car wuling = new WulingFactory().getCar();
        Car tesla= new TeslaFactory().getCar();
        wuling.name();
        tesla.name();

        Car mobai = new MobaiFactory().getCar();
        mobai.name();

    }
}
