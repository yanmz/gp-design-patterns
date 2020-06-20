package com.log.jul;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.helpers.LogLog;
import org.junit.Test;

public class Log4jDemo {

        @Test
        public void test(){
            //开启 log4j内置日志记录
            LogLog.setInternalDebugging(true);
            //初始化配置信息，不使用配置文件
//            BasicConfigurator.configure();

            //获取日志记录器
            Logger logger = Logger.getLogger(Log4jDemo.class);
                //日志记录输出
                logger.info("hello xiaoyan");
                //日志级别
                logger.fatal("fatal"); //严重错误 一般会造成系统奔溃终止交易
                logger.error("error"); //错误信息不会影响系统运行
                logger.warn("warn"); //警告信息 可能会发生问题
                logger.info("info"); //运行信息 数据连接 网络连接 IO操作
                logger.debug("debug"); //调试信息 一般在开发中使用
                logger.trace("trace"); //追踪信息 记录所有流程信息

            // 自定义 com.itheima
             Logger logger1 = Logger.getLogger(Log4jDemo.class);
             logger1.fatal("fatal"); // 严重错误，一般会造成系统崩溃和终止运行
             logger1.error("error"); // 错误信息，但不会影响系统运行
             logger1.warn("warn"); // 警告信息，可能会发生问题
             logger1.info("info"); // 程序运行信息，数据库的连接、网络、IO操作等
             logger1.debug("debug"); // 调试信息，一般在开发阶段使用，记录程序的变量、参数等
             logger1.trace("trace"); // 追踪信息，记录程序的所有流程信息
            // 自定义  org.apache
             Logger logger2 = Logger.getLogger(Logger.class);
             logger2.fatal("fatal logger2"); //严重错误，一般会造成系统崩溃和终止运行
             logger2.error("error logger2"); //错误信息，但不会影响系统运行
             logger2.warn("warn logger2"); // 警告信息，可能会发生问题
             logger2.info("info logger2"); // 程序运行信息，数据库的连接、网络、IO操作等
             logger2.debug("debug logger2"); // 调试信息，一般在开发阶段使用，记录程序的变量、参 数等
             logger2.trace("trace logger2"); // 追踪信息，记录程序的所有流程信息

        }
}
