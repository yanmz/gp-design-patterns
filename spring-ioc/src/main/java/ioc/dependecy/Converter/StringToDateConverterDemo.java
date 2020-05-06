package ioc.dependecy.Converter;

import cn.hutool.core.date.DateUtil;
import ioc.dependecy.bean.definition.BeanInitializationDemo;
import ioc.dependecy.bean.definition.factory.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * @author admin
 */
@Configuration
public class StringToDateConverterDemo {

    @Resource
    StringToDateConverter StringToDateConverter;

    @Bean(value = "abc")
    public  Date a(){
        Date convert = StringToDateConverter.convert("2020-01-01");
        return convert;
    }

    public static void main(String[] args) {
        //创建BeanFactory
        AnnotationConfigApplicationContext applicationContext  = new AnnotationConfigApplicationContext();

        // 注册 Configuration Class（配置类）
        applicationContext.register(StringToDateConverterDemo.class);
        applicationContext.refresh();
        Object abc = applicationContext.getBean("abc");
        System.out.println(abc);
        // 启动 Spring 应用上下文
        applicationContext.close();
    }

    @Configuration
    class StringToDateConverter implements Converter<String, Date> {
        @Override
        public Date convert(String source) {
            try {

                return DateUtil.parse(source);
            } catch (Exception e) {
                return null;
            }
        }
    }
}
