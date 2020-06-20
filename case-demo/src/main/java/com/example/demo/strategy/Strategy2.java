package com.example.demo.strategy;

import com.example.demo.config.AbstractStrategy;
import com.example.demo.config.ApiType;
import org.springframework.stereotype.Component;

/**
 * @author cuishuoguo
 * Created on 2020/6/17.
 */
@ApiType("order:2")
@Component
public class Strategy2 implements AbstractStrategy {


    @Override
    public Object execute(Object object) {
        System.out.println(object + "----->策略2");
        return null;
    }
}
