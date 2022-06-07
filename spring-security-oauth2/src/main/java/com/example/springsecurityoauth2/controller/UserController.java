package com.example.springsecurityoauth2.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :xiaoyan
 * @description :
 * @create :2022-06-05 17:58:00
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @PostMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authorization){
        return authorization.getPrincipal();
    }
}
