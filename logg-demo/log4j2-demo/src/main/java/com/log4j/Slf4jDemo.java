package com.log4j;

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
    }
}
