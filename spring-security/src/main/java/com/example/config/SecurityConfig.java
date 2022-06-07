package com.example.config;

import com.example.handler.MyAuthenticationFailureHandler;
import com.example.handler.MyAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author :xiaoyan
 * @description :
 * @create :2022-06-04 16:15:00
 */
@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //表单提交
        http.formLogin()
                //自定义字段名
//                .usernameParameter("username123")
//                .passwordParameter("password123")
                .loginProcessingUrl("/login")
                //自定义登录页面
                .loginPage("/login.html")
                //登录成功后跳转页面post请求
//                .successForwardUrl("toMain")
                //跳转到其他网址
                .successHandler(new MyAuthenticationSuccessHandler("http//:www.baidu.com"))
//                .failureForwardUrl("toError")
                //跳转到其他网址
                .failureHandler(new MyAuthenticationFailureHandler("http//:www.baidu.com"));

        //授权认证
        http.authorizeRequests()
                //放行/login.html 允许被访问
                .antMatchers("/login.html").permitAll()
                //放行/error.html 允许被访问
                .antMatchers("/error.html").permitAll()
                //拥有一个权限才能访问
//                .antMatchers("/main1.html").hasAuthority("admin")
                //拥有多个访问权限配置
//                .antMatchers("/main1.html").hasAnyAuthority("admin")
                //配置哪个角色可以访问
//                .antMatchers("/main1.html").hasRole("abc")
                //配置多个角色可以访问
//                .antMatchers("/main1.html").hasAnyRole("abc")
//                正则匹配
//                .regexMatchers(".+[.]jpg").permitAll()
//                .antMatchers("/js/**","/css/**","/images/**").permitAll()
//                .antMatchers("/**/*.jpg").permitAll()
//通过配置类来认证
                .anyRequest().access("@myServiceImpl.hasPermission(httpServletRequest,authentication)");
                //所有请求都必须被认证，必须登录之后才能访问
//                .anyRequest().authenticated();

        //关闭csrf防护，防止跨站请求拦截
        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
