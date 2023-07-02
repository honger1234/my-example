package com.honger1234.threadlearning.juc.volatile1;

import java.util.concurrent.TimeUnit;

public class VolatileDemo {

    public static volatile int num=0;

    public static void main(String[] args) {
        new Thread(()->{
            while (num==0){
                int i=0;
//                System.out.println("111");
            }
        }).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num=1;
        System.out.println(num);
    }
}
