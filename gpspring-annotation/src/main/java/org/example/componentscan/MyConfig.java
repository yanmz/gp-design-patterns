package org.example.componentscan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(value = "org.example.componentscan",
//                includeFilters = {@Filter(type = FilterType.ANNOTATION,value = {Controller.class})},
//                includeFilters = {@Filter(type = FilterType.ASSIGNABLE_TYPE,value = {MyController.class})},
        includeFilters = {@Filter(type = FilterType.CUSTOM, value = {GPTypeFilter.class})},
        useDefaultFilters = true
) //componet、repositoyr
/**
 * useDefaultFilters 标签为true表示使用默认的Filter进行包扫描，而默认的Filter有 @Service,@Controller和@Repository<br></>
 * 当你希望只扫描特性的注解比如@Controller不扫描@Service那么就需要使用如下两个属性来配置：
 * includeFilters = {@Filter(type = FilterType.ANNOTATION,value = {Controller.class})},
 *         useDefaultFilters = false)
 *
 */

public class MyConfig {
}
