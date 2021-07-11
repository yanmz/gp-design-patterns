package org.example.propertysource;

import org.example.propertysource.entity.Bird;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Tom.
 */
@Configuration
@PropertySource("classpath:values.properties")
public class MyConfig {

    @Bean
    public Bird bird(){
        return new Bird();
    }
}
