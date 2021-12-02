package com.honger1234.goflearning.builder;

public class Worker extends Builder{
    private Product product;

    public Worker(){
        product=new Product();
    }

    @Override
    void buildeA() {
        product.setBuilderA("地基");
        System.out.println("地基");
    }

    @Override
    void buildeB() {
        product.setBuilderB("钢筋工程");
        System.out.println("钢筋工程");
    }

    @Override
    void buildeC() {
        product.setBuilderC("铺电线");
        System.out.println("铺电线");
    }

    @Override
    void buildeD() {
        product.setBuilderD("粉刷");
        System.out.println("粉刷");
    }

    @Override
    Product genProduct() {
        return product;
    }
}
