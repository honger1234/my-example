package com.honger1234.goflearning.factory.method;

public class MobaiFactory implements CarFactory{
    @Override
    public Car getCar() {
        return new Mobai();
    }
}
