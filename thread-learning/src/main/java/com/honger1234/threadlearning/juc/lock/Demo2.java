package com.honger1234.threadlearning.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * 3.增加了一个普通方法,先发短信还是Hello
 * 4.两个对象,两个同步方法,先发短信还是先打电话
 * 不同对象是不同的锁，不同的锁不会阻塞
 */
public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        //两个对象,两个调用者,两把锁!
        Phone2 phone = new Phone2();
        Phone2 phone2 = new Phone2();
        //锁的存在
        new Thread(() -> {
            phone.sendSms();
        }, "A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()-> {
            phone.hello();
        }).start();

        new Thread(() -> {
            phone2.call();
        }, "B").start();
    }
}

class Phone2 {
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

    //这里没有锁,不是同步方法,不受锁的影响
    public void hello() {
        System.out.println("hello");
    }
}
