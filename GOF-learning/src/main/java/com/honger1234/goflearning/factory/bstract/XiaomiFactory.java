package com.honger1234.goflearning.factory.bstract;

public class XiaomiFactory implements IProductFactory{
    @Override
    public IPhoneProduct iphoneProduct() {
        return new XiaomiPhone();
    }

    @Override
    public IRouterProduct irouterProduct() {
        return new XiaomiRouter();
    }
}
