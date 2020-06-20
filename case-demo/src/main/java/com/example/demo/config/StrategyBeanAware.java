package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cuishuoguo
 * Created on 2019/11/11.
 */
@Component
@Slf4j
public class StrategyBeanAware implements BeanFactoryAware {


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        try {
            log.info("ღღღღღღღღღღღღღღღღ:BeanFactoryAware is loading");
            HandlerContext handlerContext = (HandlerContext) beanFactory.getBean("handlerContext");
            Map<String, Class> map = handlerContext.getMap();
            for (String p : map.keySet()) {
                log.info("【策略类】{},加载成功，别名{}", map.get(p), p);
            }
            Map<String, AbstractStrategy> beanMap = new HashMap<>(map.size());
            for (String key : map.keySet()) {
                AbstractStrategy bean = (AbstractStrategy) beanFactory.getBean(map.get(key));
                beanMap.put(key, bean);
            }
            handlerContext.setBeanMap(beanMap);
            log.info("ღღღღღღღღღღღღღღღღ:BeanFactoryAware is end");
        } catch (NoSuchBeanDefinitionException e) {
            log.error("ღღღღღღღღღღღღღღღღ:handlerContext is unloading", e.getMessage());
        }
    }
}
