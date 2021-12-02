package com.honger1234.goflearning.factory.simple;

import java.util.Objects;

public class CarFactory {

    public static Car getCar(String car){
        if (Objects.equals(car,"wuling")){
            return new Wuling();
        }else if (Objects.equals(car,"tesla")){
            return new Tesla();
        }
        return null;
    }
}
