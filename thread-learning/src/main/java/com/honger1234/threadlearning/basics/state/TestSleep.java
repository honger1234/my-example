package com.honger1234.threadlearning.basics.state;

/**
 * 模拟网络延时：放大问题的发生性
 */
public class TestSleep implements Runnable {
    //票数
    private int ticketNums = 10;

    @Override
    public void run() {
        while (true) {
            if (ticketNums <= 0) {
                break;
            }
            //模拟延时
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "--->拿到了第" + ticketNums-- + "票");
        }

    }

    public static void main(String[] args) {
        TestSleep testSleep = new TestSleep();

        new Thread(testSleep,"小明").start();
        new Thread(testSleep,"小红").start();
        new Thread(testSleep,"小黄牛").start();

    }
}
