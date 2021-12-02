package com.honger1234.goflearning.builder;

public class Direct {
    public Product build(Builder builder){
        builder.buildeA();
        builder.buildeB();
        builder.buildeC();
        builder.buildeD();

        return builder.genProduct();
    }
}
