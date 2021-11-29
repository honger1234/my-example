package com.honger1234.threadlearning.basics.state;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestSleep2 {
    /**
     * 模拟倒计时
     *
     * @throws InterruptedException
     */
    public static void testDown() throws InterruptedException {
        int num = 10;
        while (true) {
            Thread.sleep(1000);
            System.out.println(num--);
            if (num == 0) {
                break;
            }
        }
    }

    /**
     * 打印当前时间
     */
    public static void printNowDate() {
        //打印当前系统时间
        Date stattTime = new Date(System.currentTimeMillis());
        while (true) {
            try {
                //休眠1秒
                Thread.sleep(1000);
                //格式化时间,并输出时间
                System.out.println(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(stattTime));
                //更新获取时间
                stattTime = new Date(System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        printNowDate();
    }
}
