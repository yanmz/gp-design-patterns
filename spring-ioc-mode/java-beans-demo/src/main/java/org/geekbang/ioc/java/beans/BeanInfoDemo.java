package org.geekbang.ioc.java.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.util.stream.Stream;

public class BeanInfoDemo {
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class,Object.class);
        Stream.of(beanInfo.getPropertyDescriptors()).forEach(propertyDescriptors->{
//            System.out.println(propertyDescriptors);
            Class<?> propertyType = propertyDescriptors.getPropertyType();
            String name = propertyDescriptors.getName();
            if("age".equals(name)){
//                Integer.valueOf("");
                propertyDescriptors.setPropertyEditorClass(StringToIntegerPropertyEditor.class);
            }
        });
    }

    static class  StringToIntegerPropertyEditor extends PropertyEditorSupport{
        @Override
        public void setAsText(String text){
            Integer integer = Integer.valueOf(text);
            setValue(integer);
        }
    }
}
