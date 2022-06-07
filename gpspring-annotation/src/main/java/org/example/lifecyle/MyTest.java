package org.example.lifecyle;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Tom.
 * <p>
 * //3种方式
 * // 1.使用@PostConstruct和@PreDestroy注解
 * // 2.实现InitializingBean和DisposableBean接口
 * // 3.添加initMethod 和 destroyMethod
 * <p>
 * 记忆：先构造 再实现 再自定义
 */
public class MyTest {
    @Test
    public void test() {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(MyConfig.class);
        System.out.println("IoC容器创建完成\n");
        System.out.println(app.getBean("car") + "\n");
        app.close();
    }
}
