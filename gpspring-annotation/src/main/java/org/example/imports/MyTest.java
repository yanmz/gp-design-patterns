package org.example.imports;

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

        //通过FactoryBean注入的值
        System.out.println("=app.getBean(\"monkey\").getClass=" + app.getBean("monkey").getClass());
        Object monkey1 = app.getBean("monkey");
        Object monkey2 = app.getBean("monkey");
        System.out.println("测试:通过app.getBean(\"monkey\")拿到的是monkey而不是MyFactoryBean，其中money是否单例呢：" + (monkey1 == monkey2));

        //拿到构建monkey的FactoryBean
        Object monkeyFactoryBean = app.getBean("&monkey");
        System.out.println("通过&monkey拿到外面的FactoryBean：" + monkeyFactoryBean);

        String[] beanNames = app.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanNames)
                .replaceAll("\\[|\\]", "")
                .replaceAll(", ", "\n"));
    }
}
