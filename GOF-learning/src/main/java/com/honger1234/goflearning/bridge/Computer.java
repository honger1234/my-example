package com.honger1234.goflearning.bridge;

public abstract class Computer {
    private Brand brand;
    public Computer(Brand brand){
        this.brand=brand;
    }

    public void info(){
        brand.info();//自带品牌
    }

}

//台式
class Desktop extends Computer{

    public Desktop(Brand brand) {
        super(brand);
    }

    @Override
    public void info() {
        super.info();
        System.out.println("台式机");
    }
}

//笔记本
class Laptop extends Computer{

    public Laptop(Brand brand) {
        super(brand);
    }

    @Override
    public void info() {
        super.info();
        System.out.println("笔记本");
    }
}
