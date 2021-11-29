package com.honger1234.threadlearning.basics;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * 创建线程方式3：实现Callable 接口
 * 1.实现Callable接口，需要返回值类型
 * 2.重写call 方法，需要抛出异常
 * 3.创建目标对象
 * 4.创建执行服务：
 * 5.提交执行：
 * 6.获取结果：
 * 7.关闭服务：
 */
public class TestCallable implements Callable<Boolean> {

    private String url;//网络图片地址
    private String name;//保存的文件名

    public TestCallable(String url, String name) {
        this.name = name;
        this.url = url;
    }
    //下载图片线程的执行体
    @Override
    public Boolean call() throws Exception {
        WebDownloader1 webDownloader = new WebDownloader1();
        webDownloader.downLoader(url,name);
        System.out.println("下载了文件名为："+name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        TestCallable t1 = new TestCallable("https://img-blog.csdnimg.cn/img_convert/d8885c9a178b2fcaea732190717b516d.png", "4.jpg");
        TestCallable t2 = new TestCallable("https://img-blog.csdnimg.cn/img_convert/d8885c9a178b2fcaea732190717b516d.png", "5.jpg");
        TestCallable t3 = new TestCallable("https://img-blog.csdnimg.cn/img_convert/d8885c9a178b2fcaea732190717b516d.png", "6.jpg");

        //1.创建执行服务
        ExecutorService ser = Executors.newFixedThreadPool(3);
        //2.提交执行
        Future<Boolean> r1 = ser.submit(t1);
        Future<Boolean> r2 = ser.submit(t2);
        Future<Boolean> r3 = ser.submit(t3);
        //获取结果
        Boolean rs1 = r1.get();
        Boolean rs2 = r2.get();
        Boolean rs3 = r3.get();

        //关闭服务
        ser.shutdownNow();

    }
}

class WebDownloader1 {
    public void downLoader(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO 异常，Downloader方法出现问题");
        }
    }

}
