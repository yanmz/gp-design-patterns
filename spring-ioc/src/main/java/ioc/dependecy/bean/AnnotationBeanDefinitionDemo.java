package ioc.dependecy.bean;

import ioc.dependecy.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


/**
 *
 *注解 BeanDefinition 示例
 * @author Tom
 */
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {
    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册Configuration Class(配置类)
        applicationContext.register(AnnotationBeanDefinitionDemo.class);
        // 启动 Spring 应用上下文
        applicationContext.refresh();


        // 通过 BeanDefinition 注册 API 实现
        // 1.命名 Bean 的注册方式
        registerUserBeanDefinition(applicationContext, "mercyblitz-user");
        // 2. 非命名 Bean 的注册方法
        registerUserBeanDefinition(applicationContext);


        System.out.println("Config 类型的所有 Beans" + applicationContext.getBeansOfType(Config.class));
        System.out.println("User 类型的所有 Beans" + applicationContext.getBeansOfType(User.class));

        // 显示地关闭 Spring 应用上下文
        applicationContext.close();
    }

    //定义当前类作为Spring Bean组件
    @Component
    public  static  class Config{

        //通过java  @Bean注解定义一个bean
        @Bean(name = "user")
        public User user(){
            User user = new User();
            user.setId(1L);
            user.setName("小马哥");
            return user;
        }
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id",1).addPropertyValue("name","xiaoyan");
        // 判断如果 beanName 参数存在时
        if(StringUtils.hasText(beanName)){
            // 注册 BeanDefinition
            registry.registerBeanDefinition(beanName,beanDefinitionBuilder.getBeanDefinition());
        }else{
            // 非命名 Bean 注册方法
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry, null);
    }
}
