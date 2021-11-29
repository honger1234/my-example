package com.honger1234.threadlearning.basics;

/**
 * 创建线程方式2：实现Runnable接口
 */
public class TestThread3 implements Runnable{
    @Override
    public void run() {
        //run方法 线程体
        for (int i = 0; i < 10; i++) {
            System.out.println("我在撸代码--"+i);
        }
    }

    public static void main(String[] args) {
        //创建线程对象
        TestThread3 testThread3 = new TestThread3();
        //创建线程对象，通过线程对象来开启我们的线程
        Thread thread = new Thread(testThread3);
        thread.start();

        //main线程，主线程
        for (int i = 0; i < 10; i++) {
            System.out.println("我在学习--"+i);
        }

    }
}
