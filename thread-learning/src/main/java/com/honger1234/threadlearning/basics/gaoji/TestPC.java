package com.honger1234.threadlearning.basics.gaoji;

/**
 * 并发协作模型“生产者/消费者模式”—>管程法
 * 1.生产者:负责生产数据的模块(可能是方法﹐对象﹐线程,进程);
 * 2.消费者∶负责处理数据的模块(可能是方法,对象﹐线程﹐进程);
 * 3.缓冲区:消费者不能直接使用生产者的数据﹐他们之间有个“缓冲区
 *
 * 生产者将生产好的数据放入缓冲区,消费者从缓冲区拿出数据
 */
public class TestPC {
    public static void main(String[] args) {

        SynContainer container = new SynContainer();

        new Productor(container).start();
        new Consumer(container).start();

    }
}

//生产者
class Productor extends Thread {
    SynContainer container;

    public Productor(SynContainer container) {
        this.container = container;
    }
    //生产
    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            container.push(new Chiken(i));
            System.out.println("生产了"+i+"只鸡");
        }
    }
}


/**
 * 消费者
 */
class Consumer extends Thread {
    SynContainer container;

    public Consumer(SynContainer container) {
        this.container = container;
    }
    /**
     * 生产
     */
    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            Chiken pop = container.pop();
            System.out.println("消费了第"+pop.id+"只鸡");
        }
    }
}

//产品
class Chiken {
    int id;//编号

    public Chiken(int id) {
        this.id = id;
    }
}

//缓冲区
class SynContainer {

    //需要一个容器大小
    Chiken[] chikens = new Chiken[10];
    //容器计数器
    int count = 0;

    //生产者放入产品
    public synchronized void push(Chiken chiken) {
        //如果容器满了，就需要等待消费者消费
        if (count == chikens.length) {
            //通知消费者消费，生产者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果容器没有满，我们就需要丢入产品
        chikens[count] = chiken;
        count++;
        //通知消费者消费
        this.notifyAll();
    }

    //通知消费者消费
    public synchronized Chiken pop() {
        //判断能否消费
        if (count == 0) {
            //等待生产者生产，消费者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果可以消费
        count--;
        Chiken chiken = chikens[count];
        //吃完了，通知生产者生产
        this.notifyAll();
        return chiken;
    }
}
