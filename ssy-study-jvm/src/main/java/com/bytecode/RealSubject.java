package com.bytecode;

public class RealSubject  implements  Subject{

    @Override
    public void request() {
        System.out.println("this is RealSubject");
    }
}
