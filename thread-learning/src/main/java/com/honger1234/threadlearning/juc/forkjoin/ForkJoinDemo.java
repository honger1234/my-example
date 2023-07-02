package com.honger1234.threadlearning.juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

class MyTask extends RecursiveTask<Long> {

    //拆分差值不能超过10，计算10以内运算
    private static final Long VALUE = 10000L;
    private Long begin ;//拆分开始值
    private Long end;//拆分结束值
    private Long result=0L ; //返回结果

    //创建有参数构造
    public MyTask(Long begin,Long end) {
        this.begin = begin;
        this.end = end;
    }

    //拆分和合并过程
    @Override
    protected Long compute() {
        //判断相加两个数值是否大于10
        if((end-begin)<=VALUE) {
            //相加操作
            for (Long i = begin; i <=end; i++) {
                result = result+i;
            }
        } else {//进一步拆分
            //获取中间值
            Long middle = (begin+end)/2;
            //拆分左边
            MyTask task01 = new MyTask(begin,middle);
            //拆分右边
            MyTask task02 = new MyTask(middle+1,end);
            //调用方法拆分
            task01.fork();
            task02.fork();
            //合并结果
            result = task01.join()+task02.join();
        }
        return result;
    }
}

public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        //创建MyTask对象
        MyTask myTask = new MyTask(0L,10_0000_0000L);
        //创建分支合并池对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> forkJoinTask = forkJoinPool.submit(myTask);
        //获取最终合并之后结果
        Long result = forkJoinTask.get();
        long end = System.currentTimeMillis();
        String time=end-start+"";
        System.out.println("结果:"+result+",花费时间:"+time);
        //关闭池对象
        forkJoinPool.shutdown();
        test3();
    }

    /**
     * 使用了Java 8中的流（Stream）和并行流（parallel stream）来计算从0到10亿的所有数字的和
     * 代码中使用了LongStream.rangeClosed来生成一个从0到10亿的长整型流，然后使用parallel方法将流转换为并行流，接着使用reduce方法对流中的所有元素进行求和操作
     */
    public static void test3(){
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0L,10_0000_0000L).parallel().reduce(0,Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("结果:"+sum+",花费时间:"+(end-start));
    }
}
