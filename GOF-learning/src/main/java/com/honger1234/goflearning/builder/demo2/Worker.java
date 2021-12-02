package com.honger1234.goflearning.builder.demo2;


public class Worker extends Builder {
    private Product product;

    public Worker() {
        product = new Product();
    }


    @Override
    Builder buildeA(String msg) {
        product.setBuilderA(msg);
        return this;
    }

    @Override
    Builder buildeB(String msg) {
        product.setBuilderB(msg);
        return this;
    }

    @Override
    Builder buildeC(String msg) {
        product.setBuilderC(msg);
        return this;
    }

    @Override
    Builder buildeD(String msg) {
        product.setBuilderD(msg);
        return this;
    }

    @Override
    Product genProduct() {
        return product;
    }
}
