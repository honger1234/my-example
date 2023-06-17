package com.honger1234.threadlearning.basics.state;

/**
 * 线程状态
 */
public class TeestState {
    public static void main(String[] args) throws InterruptedException {

        Thread thread =  new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            System.out.println("/");
        });

        //观察状态
        Thread.State state = thread.getState();
        System.out.println(state);

        //观察启动后
        thread.start();
        state = thread.getState();
        System.out.println(state);
        //只要线程不终止，就一直输出状态
        while (state != Thread.State.TERMINATED){
            Thread.sleep(100);
            state = thread.getState();//更新线程状态
            System.out.println(state);//输出线程状态
        }

    }
}
