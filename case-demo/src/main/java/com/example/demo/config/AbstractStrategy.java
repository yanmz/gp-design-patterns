package com.example.demo.config;

/**
 * @author cuishuoguo
 * Created on 2019/11/11.
 * ceshi
 */
public interface AbstractStrategy {

    /**
     * 执行方法
     *
     * @param object 需要处理的参数
     */
    Object execute(Object object);
}
