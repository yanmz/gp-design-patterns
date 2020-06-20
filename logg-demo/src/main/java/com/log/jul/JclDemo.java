package com.log.jul;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class JclDemo {
    @Test
    public void test(){
        // 创建日志对象
        Log log = LogFactory.getLog(JclDemo.class);
        // 日志记录输出
        log.fatal("fatal");
    }
}
