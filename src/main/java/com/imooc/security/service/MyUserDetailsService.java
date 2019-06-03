package com.imooc.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("登录用户名是:" + username);
        //注意这里的User是SS自带的User对象,已经实现了UserDetails接口
        //第三个表示用户的权限,这个是关联到SS配置里的auth开头的那些方法需要的权限
        //调用了SS自己提供的工具方法把字符串转成需要的对象
        //实际开发的时候换成数据库

        System.out.println(passwordEncoder().encode("cony"));
        //需要验证逻辑的时候,要换构造函数,加了四个boolean对应四个验证逻辑
        //这里不管什么逻辑,最后把UserDetails对象给弄成任意一个验证逻辑无效,就无法通过认证
        User targetUser = new User(username,
                passwordEncoder().encode("cony"),
                true,
                true,
                true,
                true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin")
        );
        return targetUser;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}




















