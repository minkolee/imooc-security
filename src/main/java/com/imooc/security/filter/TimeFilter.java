package com.imooc.security.filter;

//自定义过滤器，拦截REST 请求

//有三种，filter，拦截器和切片
//filter是J2EE规范
//拦截器是Spring 本身实现的对于请求和响应的东西，可以知道控制器类和方法，还可以获取异常
//切片是单独的包
//目的，三种方法实现记录请求的时间

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

//声明为一个Bean就自动可以启动过滤器，无需其他配置
//过滤器默认对所有的服务请求都会经过过滤器
//如果第三方过滤器，可以通过web.xml加到项目中去
//Spring Boot不使用XML，则可以通过Java配置来实现，看WebConfig文件。这里需要去掉@Component注解
//@Component
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Time 过滤器启动");
    }

    @Override
    public void destroy() {
        System.out.println("Time 过滤器结束");
    }

    //过滤器方法
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Time Filter 开始工作");

        long start = new Date().getTime();

        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println("Time Filter 结束工作， 用时" + (new Date().getTime() - start));
    }
}
