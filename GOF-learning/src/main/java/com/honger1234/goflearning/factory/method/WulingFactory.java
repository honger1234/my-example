package com.honger1234.goflearning.factory.method;

public class WulingFactory implements CarFactory{
    @Override
    public Car getCar() {
        return new Wuling();
    }
}
