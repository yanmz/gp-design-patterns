package com.log.jul;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;

/**
 * JUL 默认级别info
 */
public class JulDemo {

    @Test
    public void test() {
        //获取日志记录器对象
        Logger logger = Logger.getLogger("MyTest1");

        //日志记录的输出
        logger.info("hello jul");

        //通用方法进行日志记录
        logger.log(Level.INFO, "info msg");

        //通过占位符 方式输出变量值
        logger.log(Level.INFO, "用户信息:{0},{1}", new String[]{"xiaoyan", "22"});
    }

    @Test
    public void test1() {
        Logger logger = Logger.getLogger("MyTest1");
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

    @Test
    public void test2() {
        Logger logger = Logger.getLogger("MyTest1");
        //关闭系统默认配置
        logger.setUseParentHandlers(false);
        //自定义配置日志级别
        //创建consoleHandler 控制台输出
        ConsoleHandler consoleHandler = new ConsoleHandler();

        //创建简单格式转换对象
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        //进行关联
        consoleHandler.setFormatter(simpleFormatter);
        logger.addHandler(consoleHandler);

        //配置日志具体级别
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);

        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }

    //用配置文件设置自定义级别
    @Test
    public void test3() throws IOException {
        //获取资源文件
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("logging.properties");

        //创建LogManager
        LogManager logManager = LogManager.getLogManager();

        //通过LogManager加载配置
        logManager.readConfiguration(inputStream);

        //创建日志记录器
        Logger logger = Logger.getLogger("com.MyTest1");
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");
    }
}
