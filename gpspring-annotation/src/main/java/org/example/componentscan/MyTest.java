package org.example.componentscan;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * Created by Tom.
 */
public class MyTest {
    @Test
    public void test() {
        ApplicationContext app = new AnnotationConfigApplicationContext(MyConfig.class);
        String[] beanNames = app.getBeanDefinitionNames();
        System.out.println(">>>最终Spring容器中的对象为：");
        System.out.println(Arrays.toString(beanNames)
                .replaceAll("\\[|\\]", "")
                .replaceAll(", ", "\n"));
    }
}
