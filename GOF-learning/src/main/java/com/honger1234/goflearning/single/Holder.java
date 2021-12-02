package com.honger1234.goflearning.single;

/**
 * 静态内部类实现单例
 */
public class Holder {
    private Holder() {
    }
    public static Holder instance(){
        return inner.holder;
    }

    private static class inner{
        private static Holder holder=new Holder();
    }

    public static void main(String[] args) {
        Holder holder = new Holder();
        Holder instance = Holder.instance();
        Holder instance1 = Holder.instance();
        System.out.println(holder);
        System.out.println(instance);
        System.out.println(instance1);
    }
}
