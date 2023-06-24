package com.honger1234.threadlearning.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 使用callable启动线程
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread thread = new MyThread();
        FutureTask futureTask = new FutureTask(thread);//适配类
//        FutureTask futureTask1 = new FutureTask<>(thread);
        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start();
        //获取callable的返回结果,get方法可能产生阻塞!把他放到最后或者使用异步通信来处理
        Integer o = (Integer) futureTask.get();
        System.out.println(o);
    }
}

class MyThread implements Callable<Integer> {
    @Override
    public Integer call() {
        System.out.println("call()");
        return 1024;
    }
}
