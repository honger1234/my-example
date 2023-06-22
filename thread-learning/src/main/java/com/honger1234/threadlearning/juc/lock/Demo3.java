package com.honger1234.threadlearning.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * 5.增加两个静态的同步方法,只有一个对象,先打印 发短信还是打电话
 * 6.两个对象!增加两个静态的同步方法, 先打印 发短信还是打电话
 * synchronized加载static方法上锁的是class，对个对象的class是同一个，所以锁也是同一把，会阻塞
 */
public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        //两个对象的Class类模板只有一个,static,锁的是Class
        Phone3 phone = new Phone3();
        Phone3 phone2 = new Phone3();
        //锁的存在
        new Thread(() -> {
            phone.sendSms();
        }, "A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(() -> {
            phone2.call();
        }, "B").start();
    }
}

//Phone唯一的一个Class对象
class Phone3 {
    //synchronized 锁的对象是方法的调用者
    //static 静态方法
    //类一加载就有了!锁的是Class
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public static synchronized void call() {
        System.out.println("打电话");
    }
}
