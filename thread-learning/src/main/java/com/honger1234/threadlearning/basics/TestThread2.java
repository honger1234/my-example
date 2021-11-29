package com.honger1234.threadlearning.basics;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 实现多线程同步下图片
 */
public class TestThread2 extends Thread{

    private String url;//网络图片地址
    private String name;//保存的文件名

    public TestThread2(String url,String name){
        this.name = name;
        this.url = url;
    }

    /**
     * 下载图片线程的执行体
     */
    @Override
    public void run(){
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downLoader(url,name);
        System.out.println("下载了文件名为："+name);
    }

    public static void main(String[] args) {
        TestThread2 thread1 = new TestThread2("https://img-blog.csdnimg.cn/img_convert/d8885c9a178b2fcaea732190717b516d.png", "1.jpg");
        TestThread2 thread2 = new TestThread2("https://img-blog.csdnimg.cn/img_convert/d8885c9a178b2fcaea732190717b516d.png", "2.jpg");
        TestThread2 thread3 = new TestThread2("https://img-blog.csdnimg.cn/img_convert/d8885c9a178b2fcaea732190717b516d.png", "3.jpg");
        //先下载t1
        thread1.start();
        //先下载t2
        thread2.start();
        //先下载t3
        thread3.start();
    }
}

class WebDownloader{
    public void  downLoader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO 异常，Downloader方法出现问题");
        }
    }

}
