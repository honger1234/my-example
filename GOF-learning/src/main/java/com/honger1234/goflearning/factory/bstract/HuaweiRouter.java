package com.honger1234.goflearning.factory.bstract;

public class HuaweiRouter implements IRouterProduct{


    @Override
    public void start() {
        System.out.println("华为路由开机");
    }

    @Override
    public void shutdown() {
        System.out.println("华为路由关机");
    }

    @Override
    public void openWifi() {
        System.out.println("华为路由打开WiFi");
    }

    @Override
    public void setting() {
        System.out.println("华为路由设置");
    }
}
