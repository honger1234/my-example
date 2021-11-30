package com.honger1234.threadlearning.basics.state;

/**
 * 1.线程分为用户线程和守护线程
 * 2.虚拟机必须确保用户线程执行完毕
 * 3.虚拟机不用等待守护线程执行完毕
 * 如：后台记录操作日志、监控内存、垃圾回收等等…
 */
public class TestDaemon {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        Thread thread = new Thread(god);
        thread.setDaemon(true);//默认是false表示是用户线程，正常的线程都是用户线程


        thread.start();//上帝守护线程启动

        new Thread(you).start();//你 用户线程启动

    }
}

//你
class You implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("你一生都开心活着");
        }
        System.out.println("=========goodbye! world=======");
    }
}

//上帝
class God implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("上帝保护着你");
        }
    }
}
