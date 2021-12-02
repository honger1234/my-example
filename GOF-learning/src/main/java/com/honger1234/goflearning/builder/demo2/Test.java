package com.honger1234.goflearning.builder.demo2;

public class Test {
    public static void main(String[] args) {
        Product product = new Worker().genProduct();
        System.out.println(product);

        Product product1 = new Worker().buildeA("全家桶").buildeB("鸡翅").genProduct();
        System.out.println(product1);
    }
}
