package com.honger1234.goflearning.factory.bstract;

public class Client {
    public static void main(String[] args) {
        System.out.println("===============小米产品===============");
        IPhoneProduct iPhoneProduct = new XiaomiFactory().iphoneProduct();
        iPhoneProduct.start();
        IRouterProduct iRouterProduct = new XiaomiFactory().irouterProduct();
        iRouterProduct.start();

        System.out.println("===============华为产品===============");
        IPhoneProduct iPhoneProduct1 = new HuaweiFactory().iphoneProduct();
        IRouterProduct iRouterProduct1 = new HuaweiFactory().irouterProduct();
        iPhoneProduct1.start();
        iRouterProduct1.start();

    }
}
