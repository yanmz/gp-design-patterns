package com.rudecrab.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yanmz
 * @version 1.0
 * @date 2020/8/4 9:12
 */

/**
 * SpringBoot参数校验 + 全局异常处理 + 数据统一响应一般步骤
 * 1.通过 Validator+自动抛出异常来完成参数检验
 * 2.通过全局异常处理和自定义异常完成异常操作规范
 * 3.通过数据统一响应完成响应数据规范
 *
 * @author Tom
 */
@SpringBootApplication
public class UserApplicationBoot {1

    public static void main(String[] args) {
        SpringApplication.run(UserApplicationBoot.class, args);
    }
}
