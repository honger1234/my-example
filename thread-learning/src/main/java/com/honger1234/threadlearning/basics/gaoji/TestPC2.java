package com.honger1234.threadlearning.basics.gaoji;

/**
 * wait()：释放锁，线程进入休眠状态等待唤醒
 * 例： object.wait(); 释放object对象的锁并且本线程进入休眠状态，等待其他获得object对象的锁的线程执行notify()/notifyAll()方法进行唤醒。
 * wait(long timeout)：指定等待的毫秒数
 * 如果一个线程调用共享对象的该方法挂起后，没有在指定的timeout ms 内被其他线程调用该共享变量的notify()或者 notifyAll() 方法唤醒，那么该函数还是会因为超时而返回。如果将 timeout置为0则和wait方法效果一样，因为在 wait()方法内部就是调用了 wait(0)。
 * notify()：唤醒同一个对象上一个处于等待状态的线程，随机唤醒
 * notifyAll()：唤醒同一个对象上所有调用wait()方法的线程,优先级别高的线程优先调度
 *
 *
 *
 */
public class TestPC2 {
    public static void main(String[] args) {
        Tv tv = new Tv();
        new Player(tv).start();
        new Watcher(tv).start();
    }
}

/**
 * 演员
 */
class Player extends Thread {
    Tv tv;
    public Player(Tv tv) {
        this.tv = tv;
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                this.tv.play("前面的区域以后再来探索吧！");
            } else {
                this.tv.play("进不去！怎么想都进不去吧！");
            }
        }
    }
}

/**
 * 观众
 */
class Watcher extends Thread {
    Tv tv;
    public Watcher(Tv tv) {
        this.tv = tv;
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            tv.watch();
        }
    }
}

/**
 * 节目
 */
class Tv {
    String voice;//节目名
    boolean flag = false;
    //表演
    public synchronized void play(String voice) {
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.voice = voice;
        this.flag = !this.flag;
        System.out.println("应急食品：" + voice);
        this.notifyAll();
    }
    //观看
    public synchronized void watch() {
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("观看了：" + voice);
        //通知演员观看
        this.flag = !this.flag;
        this.notifyAll();
    }
}