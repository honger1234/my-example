package com.honger1234.threadlearning.juc.unsafe;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * 同理可证 :ConcurrentModificationException
 * 1.Set<String> set = Collections.synchronizedSet(new HashSet<>());
 * 2.Set<String> set =new CopyOnWriteArraySet<>();
 */
public class SetTest {
    public static void main(String[] args) {
        Set set=new HashSet();
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            },i+"").start();
        }
    }
}
