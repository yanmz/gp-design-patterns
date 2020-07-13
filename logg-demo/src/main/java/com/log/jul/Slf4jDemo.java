package com.log.jul;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jDemo {
    Logger logger = LoggerFactory.getLogger(Slf4jDemo.class);

    @Test
    public void test() {
        logger.error("error");
        logger.warn("warn");
        logger.info("info");//默认级别
        logger.debug("debug");
        logger.trace("tarce");

        //使用占位符输出日志信息
        String name = "111";
        int age = 18;
        logger.info("用户:{}，{}", name, age);

        //将系统异常信息输出
        try {
            int i = 10 / 0;
        } catch (Exception e) {
            logger.error("异常", e);
        }
    }
}
