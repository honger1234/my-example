package com.honger1234.threadlearning.juc.pool;

//自定义线程池创建

import java.util.concurrent.*;

/**
 * 七大参数
 * corePoolSize	- 即使空闲时仍保留在池中的线程数，除非设置 allowCoreThreadTimeOut
 * maximumPoolSize	- 池中允许的最大线程数
 * keepAliveTime	- 当线程数大于内核时，这是多余的空闲线程在终止前等待新任务的最大时间。
 * unit	- keepAliveTime参数的时间单位
 * workQueue	- 用于在执行任务之前使用的队列。 这个队列将仅保存execute方法提交的Runnable任务。
 * threadFactory	- 执行程序创建新线程时使用的工厂
 * handler	- 执行被阻止时使用的处理程序，因为达到线程限制和队列容量
 * <p>
 * 四种拒绝策略
 * ThreadPoolExecutor.AbortPolicy	被拒绝的任务的处理程序，抛出一个 RejectedExecutionException 。
 * ThreadPoolExecutor.CallerRunsPolicy	哪里来的回哪里去，会被调用线程池的线程执行
 * ThreadPoolExecutor.DiscardOldestPolicy	被拒绝的任务的处理程序，丢弃最旧的未处理请求，然后重试 execute ，除非执行程序被关闭，在这种情况下，任务被丢弃。
 * ThreadPoolExecutor.DiscardPolicy	被拒绝的任务的处理程序静默地丢弃被拒绝的任务
 */
public class ThreadPoolDemo2 {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
        );

        //10个顾客请求
        try {
            for (int i = 1; i <= 10; i++) {
                //执行
                threadPool.execute(() -> {
//                    try {
//                        TimeUnit.SECONDS.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    System.out.println(Thread.currentThread().getName() + " 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭
            threadPool.shutdown();
        }

    }


}

