package com.honger1234.goflearning.bridge;

public class Test {
    public static void main(String[] args) {
        System.out.println("=============苹果=============");
        Desktop desktop = new Desktop(new Apple());//台式机
        Laptop laptop = new Laptop(new Apple());//笔记本
        desktop.info();
        laptop.info();

        System.out.println("=============联想=============");
        Desktop desktop1 = new Desktop(new Lenovo());
        Laptop laptop1 = new Laptop(new Lenovo());
        desktop1.info();
        laptop1.info();

    }
}
