package com.example.dubbo;


/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
public class MockSayHelloService implements ISayHelloService {

    @Override
    public String sayHello(String msg) {
        return "触发服务降级";
    }
}
