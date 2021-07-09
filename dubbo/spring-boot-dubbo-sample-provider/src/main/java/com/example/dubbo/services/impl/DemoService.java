package com.example.dubbo.services.impl;

import com.example.dubbo.services.IDemoService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2227324689
 * http://www.gupaoedu.com
 **/
@DubboService
public class DemoService implements IDemoService {

    @Override
    public String getTxt() {
        return "Hello Gupaoedu.cn/8.8";
    }
}
