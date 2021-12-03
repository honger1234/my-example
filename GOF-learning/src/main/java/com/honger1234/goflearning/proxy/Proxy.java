package com.honger1234.goflearning.proxy;

/**
 * 代理角色中介
 */
public class Proxy implements Rent{
    private Landlord landlord;
    @Override
    public void rend() {
        landlord.rend();
        seeHouse();
        fare();
        hetong();
    }

    public Proxy(Landlord landlord){
        this.landlord=landlord;
    }

    public void seeHouse(){
        System.out.println("中介带你看房子");
    }
    public void fare(){
        System.out.println("收中介费");
    }
    public void hetong(){
        System.out.println("签合同");
    }

}
