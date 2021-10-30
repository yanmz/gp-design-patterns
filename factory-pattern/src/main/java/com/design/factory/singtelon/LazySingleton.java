package com.design.factory.singtelon;

/**
 * 懒汉模式
 */
public class LazySingleton {

    private LazySingleton(){}


    private static  volatile LazySingleton lazySingleton = null;

    public LazySingleton getLazySingleton(){
        if(lazySingleton==null){
        synchronized (LazySingleton.class){
            if(lazySingleton==null){
                lazySingleton = new LazySingleton();
            }
        }
    }
        return  lazySingleton;
    }

}
