package com.honger1234.threadlearning.juc.volatile1;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileDemo2 {
    //    private volatile static int num = 0;
    //原子类的Integer
    private volatile static AtomicInteger num = new AtomicInteger();

    public static void add() {
        //        num++;//不是原子性操作,底层分好几步
        num.getAndIncrement();//AtomicInteger + 1 方法 ,CAS
    }

    public static void main(String[] args) {
        //理论上num结果为2万
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {//main gc
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}
