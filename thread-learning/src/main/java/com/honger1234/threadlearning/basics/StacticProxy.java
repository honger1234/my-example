package com.honger1234.threadlearning.basics;

/**
 * 静态代理总结：
 * 1.真实对象和代理对象都要实现同一个接口
 * 2.代理对象要代理真实角色
 * 好处：
 * 代理对象可以做很多对象做不了的事情
 * 真实对象专注做自己的事情
 */
public class StacticProxy {

    public static void main(String[] args) {
        You you = new You();//你要结婚
//        new Thread( () -> System.out.println("嘤嘤嘤")).start();//代理runnable接口
        new WeddingCompany(you).happyMarry();
    }

}
interface Marry{
    public void happyMarry();
}

class You implements Marry{

    @Override
    public void happyMarry() {
        System.out.println("张庭要结婚了");
    }
}

class WeddingCompany implements Marry{

    private Marry target;

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    private void before(){
        System.out.println("结婚之前，布置现场");
    }

    private void after(){
        System.out.println("结婚之后收尾款");
    }

    @Override
    public void happyMarry() {
        before();
        this.target.happyMarry();
        after();
    }

}