package org.example.value;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * Created by Tom.
 */
public class MyTest {
    @Test
    public void test(){
        ApplicationContext app = new AnnotationConfigApplicationContext(MyConfig.class);

        System.out.println(app.getBean("bird"));

        String [] beanNames = app.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanNames)
                .replaceAll("\\[|\\]","")
                .replaceAll(", ","\n"));
    }
}
