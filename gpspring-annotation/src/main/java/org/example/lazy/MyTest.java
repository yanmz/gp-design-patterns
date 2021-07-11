package org.example.lazy;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Tom.
 * @lazy 单例bean容器初始化就加载 而多例bean容器初始化不加载，因为多例每次调用都会生成不同的对象
 * 容器初始化加载它只会占用内存 没太多意义，lazy只针对单例bean，多例bean加了@lazy相当于单例bean加lazy效果一样，只有在调用它的时候才会初始化。
 */
public class MyTest {
    @Test
    public void test(){
        ApplicationContext app = new AnnotationConfigApplicationContext(MyConfig.class);
        System.out.println("IoC容器创建完成");
//        app.getBean("person");
        app.getBean("lazyPerson");
    }
}
