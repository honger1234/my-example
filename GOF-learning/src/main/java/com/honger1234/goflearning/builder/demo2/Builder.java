package com.honger1234.goflearning.builder.demo2;

public abstract class Builder {

    abstract Builder buildeA(String msg);
    abstract Builder buildeB(String msg);
    abstract Builder buildeC(String msg);
    abstract Builder buildeD(String msg);

    abstract Product genProduct();
}
