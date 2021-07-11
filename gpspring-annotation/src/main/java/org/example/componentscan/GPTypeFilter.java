package org.example.componentscan;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * Created by Tom.
 */
public class GPTypeFilter implements TypeFilter{

    /**
     *
     * @param metadataReader 获取当前正在操作的信息
     * @param metadataReaderFactory 获取上下文中所有的信息
     * @return
     * @throws IOException
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        //获取当前扫描到的类的注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        //获取当前扫描到的类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        //获取当前扫描到的类的资源
        Resource resource = metadataReader.getResource();
        String className = classMetadata.getClassName();
        if(className.contains("er")){
            System.out.println(">>>>className:" + className + "包含er，会注入到Spring容器中");
            return true;
        }
        System.out.println("!!!className:" + className + "不包含er，不会注入到Spring容器中");
        return false;
    }
}
