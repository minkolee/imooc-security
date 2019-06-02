package com.imooc.security.config;

import com.imooc.security.filter.TimeFilter;
import com.imooc.security.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    //TimeInterceptor声明为Bean，然后注入进来
//    @Autowired
//    private TimeInterceptor timeInterceptor;

    //拦截器注册器，把自定义的拦截器注册一下
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(timeInterceptor);
//    }

//    @Bean
//    public FilterRegistrationBean timeFilter() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//
//        TimeFilter timeFilter = new TimeFilter();
//        registrationBean.setFilter(timeFilter);
//
//
//        //与直接使用@Component注解不同的是，如此配置可以指定在哪些URL中生效。
//        List<String> urls = new ArrayList<>();
//        urls.add("/*");
//        registrationBean.setUrlPatterns(urls);
//        //如此配置之后，对于/user/1就不会生效，如果要全部生效可以使用/*
//        return registrationBean;
//    }


}

//Filter的缺点是，这是J2EE规范定义的，属于被Web容器装载的内容，而不是Spring 的IOC容器装载的内容。这个玩意会在HTTP进入到框架之前执行
//所以无法知道这个请求背后具体的控制器操作（因为@Controll的一套玩意是Spring框架而不是Web容器的），因此只能干一些一般性的活，不能细化。
