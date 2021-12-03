package com.honger1234.goflearning.proxy;

/**
 * 租客
 */
public class Tenant {
    public static void main(String[] args) {
        Proxy proxy = new Proxy(new Landlord());
        proxy.rend();
    }
}
