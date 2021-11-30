package com.honger1234.threadlearning.basics.gaoji;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试线程池
 * 1.提高响应速度（减少了创建新线程的时间)
 * 2.降低资源消耗（重复利用线程池中线程，不需要每次都创建)
 * 3.便于线程管理(…)
 * corePoolSize:核心池的大小
 * maximumPoolSize:最大线程数
 * keepAliveTime:线程没有任务时最多保持多长时间后会终止
 */
public class TestPool {
    public static void main(String[] args) {
        //1.创建服务，创建线程池
        ExecutorService service = Executors.newFixedThreadPool(10);

        //执行
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());

        //2.关闭连接
        service.shutdown();

    }
}

class MyThread implements Runnable {


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
