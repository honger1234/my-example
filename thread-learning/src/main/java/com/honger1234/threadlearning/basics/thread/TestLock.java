package com.honger1234.threadlearning.basics.thread;

/**
 * 产生死锁的四个必要条件：
 * 1.互斥条件：一个资源每次只能被一个进程使用。
 * 2.请求与保持条件：一个进程因请求资源而阻塞，对已获得的资源保持不放。
 * 3.不剥夺条件：进程已获得的资源，在未使用完之前，不能强行剥夺。
 * 4.循环等待条件：若干个进程之间形成一种头尾相接的循环等待资源关系。
 */
public class TestLock {
    public static void main(String[] args) {
        Makeup g1 = new Makeup(0, "灰姑凉");
        Makeup g2 = new Makeup(1, "白雪公主");

        g1.start();
        g2.start();


    }
}


class Lipstick {

}

//镜子
class Mirror {

}

class Makeup extends Thread {

    //需要的资源只有一份，用static 来保证只有一份
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();

    int choice;//选择
    String girlName;//使用化妆品的人

    public Makeup(int choice, String girlName) {
        this.choice = choice;
        this.girlName = girlName;
    }

    @Override
    public void run() {

        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //化妆，互相持有对方的锁，就是需要拿到对方的资源
    private void makeup() throws InterruptedException {
        if (choice == 0) {
            synchronized (lipstick) {//获得口红的锁
                System.out.println(this.girlName + "获得口红的锁");
                Thread.sleep(100);

                synchronized (mirror) {//一秒钟后想获得镜子
                    System.out.println(this.girlName + "获得镜子的锁");
                }

            }
        } else {
            synchronized (mirror) {//想获得镜子的锁
                System.out.println(this.girlName + "获得镜子的锁");
                Thread.sleep(100);
                synchronized (lipstick) {//一秒钟后想获得口红
                    System.out.println(this.girlName + "获得口红的锁");

                }

            }
        }
    }
}
