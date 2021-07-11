package org.example.imports;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by Tom.
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //所有jar报里面 类扫出来，装配到String数组就可以了对嘛
        return new String[]{"org.example.imports.entity.Car",
                            "org.example.imports.entity.Cat"};
    }
}
