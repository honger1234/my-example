package com.honger1234.threadlearning.basics.syn;

/**
 * 不安全的卖票
 */
public class UnsafeBuyTicket {

    public static void main(String[] args) {

        BuyTicket station = new BuyTicket();

        new Thread(station,"闲言").start();
        new Thread(station,"鸡哥").start();
        new Thread(station,"黄牛").start();

    }

}

class BuyTicket implements Runnable {

    //票
    private int ticketNums = 10;
    private boolean flag = true;//外部停止方式

    @Override
    public void run() {
        //买票
        while (flag) {
            buy();
        }
    }

    private void buy() {
        //判断是否有票
        if (ticketNums <= 0) {
            flag = false;
            return;
        }
        //模拟延时
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //买票
        System.out.println(Thread.currentThread().getName() + "拿到" + ticketNums-- + "张票");
    }
}
