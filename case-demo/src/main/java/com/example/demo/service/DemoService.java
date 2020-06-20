package com.example.demo.service;

import com.example.demo.config.HandlerContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author cuishuoguo
 * Created on 2020/6/17.
 */
@Service
@AllArgsConstructor
public class DemoService {

    private HandlerContext handlerContext;


    public void demo(String type) {
        handlerContext.getInstance("order:" + type).execute("执行操作");
    }


}
