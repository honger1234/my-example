package com.honger1234.threadlearning.juc.unsafe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
/**
 * 并发下 ArrayLIst 不安全的 ,Synchronized
 * 解决方案:
 * 1. List<String> list = new Vector<>();
 * 2. List<String> list = Collections.synchronizedList(new ArrayList<>());
 * 3. List<String> list = new CopyOnWriteArrayList<>();
 *
 *
 * CopyOnWrite 写入时赋值 COW 计算机程序设计领域的一种优化策略
 * 多个线程调用的时候 , list , 读取的时候 , 固定的, 写入(覆盖)
 * 在写入的时候避免覆盖 , 造成数据问题!
 * 读写分离
 */
public class UnsafeList {
    public static void main(String[] args) {
        List a=new ArrayList<>();
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
