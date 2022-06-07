package com.example.springsecurityoauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * http://localhost:7777/oauth/authorize?client_id=admin&response_type=code&redirect_uri=http://www.baidu.com&scope=all
 */

@SpringBootApplication
public class SpringSecurityOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityOauth2Application.class, args);
    }

}
