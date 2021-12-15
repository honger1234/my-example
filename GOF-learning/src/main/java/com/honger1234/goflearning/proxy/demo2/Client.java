package com.honger1234.goflearning.proxy.demo2;

public class Client {
    public static void main(String[] args) {
        Landlord landlord = new Landlord();
        ProxyInvocationHandler invocationHandler = new ProxyInvocationHandler();
        invocationHandler.setRent(landlord);
        Rent proxy = (Rent) invocationHandler.getProxy();
        proxy.rend();

    }
}
