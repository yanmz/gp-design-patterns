package com.log.jul;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogBackDemo {
  public final  static    Logger logger = LoggerFactory.getLogger(LogBackDemo.class);
    @Test
    public void test(){
        logger.error("error");
        logger.warn("warn");
        logger.info("info");
        logger.debug("debug");
        logger.trace("tarce");
    }
}
