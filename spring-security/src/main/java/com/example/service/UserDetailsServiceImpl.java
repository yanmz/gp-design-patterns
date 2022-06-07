package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author :xiaoyan
 * @description :
 * @create :2022-06-04 16:18:00
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(!"admin".equals(username)){
            throw  new RuntimeException("用户名不存在");
        }
        String password = passwordEncoder.encode("1234");
        //配置权限和角色,其中角色配置规则为ROLE_开头
        return new User(username,password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,noraml,ROLE_abc"));
    }
}
