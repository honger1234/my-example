# 参考地址

## 多线程基础
文档：
[https://blog.csdn.net/qq_42025798/article/details/119358440](https://blog.csdn.net/qq_42025798/article/details/119358440)
[https://www.kuangstudy.com/bbs/1637296056738148354#header4](https://www.kuangstudy.com/bbs/1637296056738148354#header4)
视频地址:[https://www.bilibili.com/video/BV1V4411p7EF/?spm_id_from=333.999.0.0&vd_source=a3d625a64090fc4e2e34f1ae12c724f1](https://www.bilibili.com/video/BV1V4411p7EF/?spm_id_from=333.999.0.0&vd_source=a3d625a64090fc4e2e34f1ae12c724f1)
## 多线程的JUC
文档：[https://www.kuangstudy.com/bbs/1640650143068217345#header4](https://www.kuangstudy.com/bbs/1640650143068217345#header4)
视频地址:[https://www.bilibili.com/video/BV1B7411L7tE?p=28&vd_source=a3d625a64090fc4e2e34f1ae12c724f1](https://www.bilibili.com/video/BV1B7411L7tE?p=28&vd_source=a3d625a64090fc4e2e34f1ae12c724f1)

# 自己的一些思考

1.关于线程的停止除了stop或destory、标志位，还有interrupt()方法可以停止线程

停止过程：调用interrupt()方法会让线程抛出InterruptedException，可以在catch停止执行和释放资源
限制：只有继承Thread类这种线程的创建的方式才能使用interrupt()方法

2.为什么不推荐使用jdk自带stop或destory，因为可能会导致资源无法正常释放，引发内存泄漏等安全问题（比如一个往List存放int数据的线程，用stop释放不了list，数据越来越多，会引发内存泄露等问题，
但用标记位和interrupt可以在停止执行时手动释放资源，当然正常开发中list集合一般用线程安全的CopyOnWriteArrayList替换，这样即使使用stop方法也没有安全问题，但实际开发中资源类型也是多样的，
像文件类型、网络资源这种也有相应的线程安全类替换，但这种资源也是需要正确的资源释放。例如，在使用RandomAccessFile类时，应该在使用完毕后调用close()方法关闭文件流；在使用Socket类时，应该在使用完毕后调用close()方法关闭套接字）




