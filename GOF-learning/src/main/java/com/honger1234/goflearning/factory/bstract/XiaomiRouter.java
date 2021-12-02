package com.honger1234.goflearning.factory.bstract;

public class XiaomiRouter implements IRouterProduct{


    @Override
    public void start() {
        System.out.println("小米路由开机");
    }

    @Override
    public void shutdown() {
        System.out.println("小米路由关机");
    }

    @Override
    public void openWifi() {
        System.out.println("小米路由打开WiFi");
    }

    @Override
    public void setting() {
        System.out.println("小米路由设置");
    }
}
