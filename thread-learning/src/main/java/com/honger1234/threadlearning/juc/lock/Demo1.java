package com.honger1234.threadlearning.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * 1.标准情况下，两个线程先打印发短信还是打电话?1.发短信﹑2.打电话
 * 2.sendSms延迟4秒,两个线程先打印 发短信还是 打电话?1.发短信 2.打电话
 * 先发短信，即使短信睡眠了4秒，短信睡眠没有释放锁，所以打电话被阻塞
 */
public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        //锁的存在
        new Thread(() -> {
            phone.sendSms();
        }, "A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            phone.call();
        }, "B").start();
    }
}

class Phone {
    //synchronized 锁的对象是方法的调用者
    //两个方法是同一个锁,谁先拿到谁先执行
    public synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized void call() {
        System.out.println("打电话");
    }
}
