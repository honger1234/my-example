package com.honger1234.threadlearning.juc.lock;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//第一步 创建资源类，定义属性和操作方法
class Share {
    //初始值
    private int number = 0;

    private final Lock lock=new ReentrantLock();
    Condition condition=lock.newCondition();
    //+1的方法
    public  void incr() throws InterruptedException {
        //加锁
        lock.lock();
        try {
            //第二步 判断 干活 通知
            while(number != 0) { //判断number值是否是0，如果不是0，等待
                condition.await(); //在哪里睡，就在哪里醒
            }
            //如果number值是0，就+1操作
            number++;
            System.out.println(Thread.currentThread().getName()+" :: "+number);
            //通知其他线程
            condition.signalAll();
        }finally {
            //释放锁
            lock.unlock();
        }
    }

    //-1的方法
    public synchronized void decr() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while(number != 1) {
                condition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName()+" :: "+number);
            //通知其他线程
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}

public class  ThreadDemo2 {
    //第三步 创建多个线程，调用资源类的操作方法
    public static void main(String[] args) {
        Share share = new Share();
        //创建线程
        new Thread(()->{
            for (int i = 1; i <=10; i++) {
                try {
                    share.incr(); //+1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 1; i <=10; i++) {
                try {
                    share.decr(); //-1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 1; i <=10; i++) {
                try {
                    share.incr(); //+1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();

        new Thread(()->{
            for (int i = 1; i <=10; i++) {
                try {
                    share.decr(); //-1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();
    }
}

