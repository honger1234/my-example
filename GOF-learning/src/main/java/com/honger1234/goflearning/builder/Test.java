package com.honger1234.goflearning.builder;

public class Test {
    public static void main(String[] args) {
        Product build = new Direct().build(new Worker());
        System.out.println(build.toString());
    }
}
