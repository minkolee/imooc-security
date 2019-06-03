package com.imooc.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    关掉CSRF才能让REST请求不会被403挡掉
//    之后Post就可以看到错误信息了

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().permitAll()
//                .and()
//        .csrf().disable();
//    }

    //表单登录,都需要身份认证
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //默认表单登录
        http.formLogin()
                .loginPage("/imooc-signin.html")
                .successForwardUrl("/user")
                // 简单认证,浏览器里直接使用弹出框
//        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/imooc-signin.html").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
