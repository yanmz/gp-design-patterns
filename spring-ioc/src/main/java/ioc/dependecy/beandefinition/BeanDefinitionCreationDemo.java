package ioc.dependecy.beandefinition;


import ioc.dependecy.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link BeanDefinition} 构建示列
 * @author Tom
 */
public class BeanDefinitionCreationDemo {
    public static void main(String[] args) {
        //1.通过BeanDefinitionBuildeer构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        //通过属性设值
        beanDefinitionBuilder.addPropertyValue("id",1).addPropertyValue("name","小颜");
        //获取BeanDefinition实例
         BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

         //2.通过AbstractBeanDefinition以及派生类构建
        GenericBeanDefinition genericBeanDefinition  = new GenericBeanDefinition();
        //设置Bean类型
        genericBeanDefinition.setBeanClass(User.class);

        //通过 MutablePropertyValues操作属性
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();

        mutablePropertyValues.addPropertyValue("id",1);
        mutablePropertyValues.addPropertyValue("name","小颜");

        //通过Set MutablePropertyValues批量操作属性
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);
    }
}
