package com.gupaoedu.demo.spring.framework.beans.support;

import com.gupaoedu.demo.spring.framework.beans.config.GPBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GPBeanDefinitionReader {

    //保存扫描到的类信息
    private List<String> regitryBeanClasses = new ArrayList<String>();

    private Properties contextConfig = new Properties();

    public GPBeanDefinitionReader(String[] configLocations) {
        //加载配置文件
        doLoadConfig(configLocations[0]);

        //扫描配置文件中的配置的相关的类
        doScanner(contextConfig.getProperty("scanPackage"));
    }

    private void doScanner(String scanPackage) {
//jar 、 war 、zip 、rar
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.","/"));
        File classPath = new File(url.getFile());

        //当成是一个ClassPath文件夹
        for (File file : classPath.listFiles()) {
            if(file.isDirectory()){
                doScanner(scanPackage + "." + file.getName());
            }else {
                if(!file.getName().endsWith(".class")){continue;}
                //全类名 = 包名.类名
                String className = (scanPackage + "." + file.getName().replace(".class", ""));
                //Class.forName(className);
                regitryBeanClasses.add(className);
            }
        }
    }

    private void doLoadConfig(String configLocation) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(configLocation.replaceAll("classpath:",""));
        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<GPBeanDefinition> loadBeanDefinitions() {
        List<GPBeanDefinition> result = new ArrayList<GPBeanDefinition>();
         for(String className:regitryBeanClasses){
             try {
                 Class<?> beanClass = Class.forName(className);
                 if(beanClass.isInterface()){continue;}
                 //保存类对应的className(全类名)
                 //保存对应的BeanName名字
                 //1.默认是类名首字母小写
                 result.add(doCreateBeanDefinition(toLowerFirstCase(beanClass.getSimpleName()), beanClass.getName()));

                 //自定义
                 //接口注入
                 for (Class<?> i :beanClass.getInterfaces()){
                     result.add(doCreateBeanDefinition(i.getName(),beanClass.getName()));
                 }
             }catch (Exception e){
                 e.printStackTrace();
             }
         }
        return result;
    }

    private GPBeanDefinition doCreateBeanDefinition(String beanName, String beanClassName) {
        //将类信息封装到GPBeanDefinition类中
        GPBeanDefinition beanDefinition = new GPBeanDefinition();
        beanDefinition.setFactoryBeanName(beanName);
        beanDefinition.setBeanClassName(beanClassName);
        return beanDefinition;
    }

    //首字母小写
    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0]+=32;
        return String.valueOf(chars);
    }
}
