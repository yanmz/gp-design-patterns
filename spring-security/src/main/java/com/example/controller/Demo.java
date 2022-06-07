package com.example.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :xiaoyan
 * @description :
 * @create :2022-06-04 19:28:00
 */

/**
 *
 *   @Secured("") 专门用于判断是否具有角色的，可以标注在方法或者类上 参数以ROLE_开头
 *   @PreAuthorize("")  @PostAuthorize(“”)  可以标注方法或者类上
 *  @PreAuthorize("")表示访问方法或者类在执行之前先判断权限，大多数情况下都是使用该注解，注解的参数和access()方法参数取值相同，都是权限表达式
 *
 */
@RestController
public class Demo {

    @PostMapping("/")
    public void  test(){

    }


    @Secured("ROLE_abc")
    @PostMapping("/toMain")
    public String toMain(){
        return  "toMain";
    }

    @PostMapping("/toError")
    public String toError(){
        return  "toError";
    }
}
