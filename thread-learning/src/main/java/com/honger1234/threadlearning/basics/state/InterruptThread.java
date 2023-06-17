package com.honger1234.threadlearning.basics.state;

/**
 * 使用interrupt()方法停止线程
 *
 * 1.关于线程的停止除了stop或destory、标志位，还有interrupt()方法可以停止线程
 *
 * 停止过程：调用interrupt()方法会让线程抛出InterruptedException，可以在catch停止执行和释放资源
 * 限制：只有继承Thread类这种线程的创建的方式才能使用interrupt()方法
 */
public class InterruptThread extends Thread{
    @Override
    public void run() {
        // 执行阻塞操作，例如等待一段时间
        try {
            Thread.sleep(5000);
            // 继续执行其他操作
            System.out.println("阻塞操作完成，继续执行其他操作");
        } catch (InterruptedException e) {
            e.printStackTrace();
            // 捕获InterruptedException异常
            System.out.println("线程被中断，停止执行");
//            return;
        }
        System.out.println("虽然抛出了异常但catch里面没有return照样往下执行，继续执行其他操作");
    }

    public static void main(String[] args) {
        //启动线程
        InterruptThread target = new InterruptThread();
        target.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        target.interrupt();
    }
}
