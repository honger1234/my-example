package com.honger1234.threadlearning.juc.ThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 * 测试ThreadLocal本地变量
 * ThreadLocal允许每个线程拥有其自己的独立变量副本，下面每个线程的输出一定是先null后1
 */
public class ThreadLocalDemo1 {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread Name= " + Thread.currentThread().getName() + " threadLocal = " + threadLocal.get());
                threadLocal.set(1);
                System.out.println("Thread Name= " + Thread.currentThread().getName() + " threadLocal = " + threadLocal.get());
            }, "" + i).start();

        }
    }
}
