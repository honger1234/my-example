package com.honger1234.goflearning.factory.bstract;

public class HuaweiFactory implements IProductFactory{
    @Override
    public IPhoneProduct iphoneProduct() {
        return new HuaweiPhone();
    }

    @Override
    public IRouterProduct irouterProduct() {
        return new HuaweiRouter();
    }
}
