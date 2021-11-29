package com.honger1234.threadlearning.basics.state;

/**
 * 1.礼让线程，让当前正在执行的线程暂停，但不阻塞
 * 2.将线程从运行状态转为就绪状态
 * 3.让cpu 重新调度，礼让不一定成功！看cpu心情
 */
public class TestYield {
    public static void main(String[] args) {
        MyYield myYield = new MyYield();

        new Thread(myYield,"A").start();
        new Thread(myYield,"B").start();

    }
}


class MyYield implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"线程开始执行");
        Thread.yield();//礼让
        System.out.println(Thread.currentThread().getName()+"线程停止执行");
    }

}
