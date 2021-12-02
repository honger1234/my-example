package com.honger1234.goflearning.factory.simple;

public class Consumer {

    public static void main(String[] args) {

//        Wuling wuling = new Wuling();
//        Tesla tesla = new Tesla();
//        wuling.name();
//        tesla.name();

        Car wuling = CarFactory.getCar("wuling");
        Car tesla = CarFactory.getCar("tesla");
        wuling.name();
        tesla.name();
    }
}
