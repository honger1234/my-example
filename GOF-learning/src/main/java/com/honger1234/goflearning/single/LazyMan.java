package com.honger1234.goflearning.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 懒汉式单例
 * 多线程并发情况下会出现问题，添加双重锁解决多线程问题，
 * 双重锁情况下仍会存在反射破坏单例问题，添加三重锁解决
 */
public class LazyMan {
    private static boolean qingjiang=false;
    private LazyMan(){

        synchronized (LazyMan.class){
            if (!qingjiang){
                qingjiang=true;
            }else {
                throw new RuntimeException("不要试图用反射破坏单例安全");
            }
        }
        System.out.println(Thread.currentThread().getName()+":"+"ok");
    }

    private static volatile LazyMan lazyMan;

    public static LazyMan getInstance(){
        //双重检测下的懒汉式单例，简称DCL懒汉式（Double Check Lazy）
        if(lazyMan==null){
            synchronized (LazyMan.class){
                if (lazyMan==null){
                    lazyMan=new LazyMan();//不是原子性操作
                }
            }
        }
        return lazyMan;
    }
    /*
     * 1.分配内存空间
     * 2、执行构造方法，初始化对象
     * 3、把这个对象指向者个空间
     *
     * 123
     * 132 A
     *
     *     B //此时lazyMan还没有完成构造
     *
     * */

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //单例不安全,反射会破坏
//        LazyMan instance = LazyMan.getInstance();
//        System.out.println(instance);
        Constructor<LazyMan> declaredConstructors = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructors.setAccessible(true);//无视私有构造器
        LazyMan lazyMan = declaredConstructors.newInstance();
        LazyMan lazyMan2 = declaredConstructors.newInstance();
        System.out.println(lazyMan);
        System.out.println(lazyMan2);

//        for (int i = 0; i < 5; i++) {
//            new Thread(()->{
//                LazyMan.getInstance();
//            }).start();
//        }
    }
}
