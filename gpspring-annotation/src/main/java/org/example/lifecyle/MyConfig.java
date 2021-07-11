package org.example.lifecyle;

import org.example.lifecyle.entity.Car;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(value = "org.example.lifecyle")
public class MyConfig {

    @Bean(initMethod = "addOil",destroyMethod = "close")
    public Car car(){
        return new Car();
    }

    //3种方式
    // 1.添加initMethod 和 destroyMethod
    // 2.实现InitializingBean和DisposableBean接口
    // 3.使用@PostConstruct和@PreDestroy注解
}
