package com.honger1234.threadlearning.juc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//演示线程池三种常用分类

/**
 * 线程池的三大方法，七大参数和
 */
public class ThreadPoolDemo1 {
    public static void main(String[] args) {
        //创建一个固定的线程池的大小
        ExecutorService threadPool1 = Executors.newFixedThreadPool(5); //5个窗口
        //单个线程
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor(); //一个窗口
        //一池可扩容线程，可伸缩的,遇强则强,遇弱则弱,
        ExecutorService threadPool3 = Executors.newCachedThreadPool();
        //10个顾客请求
        try {
            for (int i = 1; i <=100; i++) {
                //执行
                threadPool1.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" 办理业务");
                });
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭
            threadPool1.shutdown();
        }

    }

}
