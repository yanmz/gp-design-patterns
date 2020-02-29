package ioc.dependecy.caontainer;

import ioc.dependecy.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * 注解能力 {@link ApplicationContext} 作为 IoC 容器示例
 * @author Tom
 */
public class AnnotationApplicationContextIoCContainerDemo {
    public static void main(String[] args) {
        //创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类AnnotationApplicationContextIoCContainerDemo 作为配置类
        applicationContext.register(AnnotationApplicationContextIoCContainerDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        LookupCollectionByType(applicationContext);
        applicationContext.close();
    }

    @Bean
    public User user(){
        User user = new User();
        user.setId(1L);
        user.setName("小颜");
       return user;
    }

    private static void LookupCollectionByType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory  listableBeanFactory= (ListableBeanFactory)beanFactory;
            Map<String, User> userMap = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有的 User 集合对象:"+userMap);
        }
    }
}
