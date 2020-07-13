package ioc.dependecy.caontainer;

import ioc.dependecy.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * IOC容器示列
 */
public class IoCContainerDemo {
    public static void main(String[] args) {
        //创建beanfactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //xml 配置文件ClassPath路径
        String location = "META-INF/dependency-lookup-context.xml";
        int i = reader.loadBeanDefinitions(location);
        System.out.println("Bean定义加载的对象 " + i);
        LookupCollectionByType(beanFactory);

    }

    private static void LookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到的所有的 User 集合对象:" + userMap);
        }
    }
}
