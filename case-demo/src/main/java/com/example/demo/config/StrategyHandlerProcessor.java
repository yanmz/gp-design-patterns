package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cuishuoguo
 * Created on 2019/11/11.
 */
@Component
@Slf4j
public class StrategyHandlerProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        try {
            Map<String, Class> map = new HashMap<>();
            Map<String, Class> classMap = StrategyClassLoader.scan("com.example.demo.strategy", map);
            log.info("ღღღღღღღღღღღღღღღღ:classMap size" + classMap);
            HandlerContext handlerContext = new HandlerContext(classMap);
            beanFactory.registerSingleton("handlerContext", handlerContext);
            log.info("ღღღღღღღღღღღღღღღღ:handlerContext is loading");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
