package com.example.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author :xiaoyan
 * @description :
 * @create :2022-06-05 16:12:00
 */
@Service
public class MyServiceImpl implements  MyService
{
    @Override
    public Boolean hasPermission(HttpServletRequest httpServletRequest, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if(principal instanceof UserDetails){
            UserDetails userDetails = (UserDetails) principal;
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            boolean contains = authorities.contains(new SimpleGrantedAuthority(httpServletRequest.getRequestURI()));
            return  contains;
        }
        return false;
    }
}
