package com.example.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author :xiaoyan
 * @description :
 * @create :2022-06-05 16:10:00
 */
public interface MyService {
     public Boolean hasPermission(HttpServletRequest httpServletRequest, Authentication authentication);
}
