package com.honger1234.goflearning.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum EnumSingle {
    INSTANCE;
    public EnumSingle getInstance(){
        return INSTANCE;
    }

}
class test{
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        EnumSingle instance = EnumSingle.INSTANCE;
//        EnumSingle instance1 = EnumSingle.INSTANCE;
//        System.out.println(instance1);
//        System.out.println(instance);
//        System.out.println(instance == instance1);

        EnumSingle instance = EnumSingle.INSTANCE;
        Constructor<EnumSingle> declaredConstructors = EnumSingle.class.getDeclaredConstructor(String.class,int.class);
        declaredConstructors.setAccessible(true);
        EnumSingle enumSingle = declaredConstructors.newInstance();

    }
}
