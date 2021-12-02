package com.honger1234.goflearning.single;

/**
 * 饿汉式单例，提前创建对象，会浪费空间
 */
public class Hungry {

    //很费空间
    private byte[] byte1=new byte[1024*1024];
    private byte[] byte2=new byte[1024*1024];
    private byte[] byte3=new byte[1024*1024];


    private Hungry() {
    }

    private static Hungry hungry=new Hungry();

    public static Hungry getInstance(){
        return hungry;
    }
}
