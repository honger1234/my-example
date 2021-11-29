package com.honger1234.threadlearning.basics.state;

/**
 * Join 合并线程，待此线程执行完成后，再执行其他线程，其他线程阻塞
 * 可以想象成插队
 */
public class TestJoin implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("线程VIP 来了" + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        //启动线程
        thread.start();

        //主线程
        for (int i = 0; i < 200; i++) {
            if (i == 100) {
                thread.join();//插队
            }
            System.out.println("main" + i);
        }

    }
}
