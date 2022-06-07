package com.example.dubbo.services.impl;


import com.gupaoedu.springboot.dubbo.ISayHelloService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/

@DubboService(
        loadbalance = "random",
        cluster = "failover",
        retries = 2)
public class SayHelloServiceImpl implements ISayHelloService {

    @Override
    public String sayHello(String msg) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Msg:" + System.currentTimeMillis());
        return "[version1.0]-Hello," + msg + " GuPaoEdu.cn";
    }
}
