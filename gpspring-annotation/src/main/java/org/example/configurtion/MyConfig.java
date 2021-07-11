package org.example.configurtion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Tom.
 */
@Configuration
public class MyConfig {

    @Bean
    public Person person(){
        return new Person("Tom",18);
    }
}
