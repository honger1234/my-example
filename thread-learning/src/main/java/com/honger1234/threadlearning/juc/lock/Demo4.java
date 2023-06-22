package com.honger1234.threadlearning.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 *  7.1个静态的同步方法,1个普通的同步方法,1个对象,先打印谁（答案：打电话、发短信，同一个对象但锁的东西不一样，所以不会阻塞）
 *  8.1个静态的同步方法,1个普通的同步方法,2个对象,先打印谁（答案：打电话、发短信，锁的东西不一样，不管多少个对象都不会阻塞）
 */
public class Demo4 {
    public static void main(String[] args) throws InterruptedException {
        //两个对象的Class类模板只有一个,static,锁的是Class
        Phone4 phone = new Phone4();
        Phone4 phone2 = new Phone4();
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
class Phone4 {
    //静态的同步方法  锁的是Class类模板
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    //普通的同步方法   锁的调用者
    public synchronized void call() {
        System.out.println("打电话");
    }
}
