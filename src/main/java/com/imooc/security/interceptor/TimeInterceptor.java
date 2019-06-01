package com.imooc.security.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 自定义拦截器是Spring 框架提供的
 * 所以知道具体的控制器是哪一个
 * 用于在控制器前后拦截请求
 * 需要实现HandlerInterceptor接口
 * 拦截器和filter不同，filter自己是一个类，直接把所有活都干了
 * 而拦截器每次只调一个方法，所以要在方法间通信，一般需要通过在请求上加上属性
 * 拦截器还需要通过Java配置才能生效
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

    //在控制器方法执行之前调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("控制器方法执行前");
        //第三个参数handler是一大 优点，就是这是将要处理这个请求的控制器对象
        //打印控制器类名
        System.out.println(((HandlerMethod) handler).getBean().getClass().getName());
        //打印控制器方法名
        System.out.println(((HandlerMethod) handler).getMethod().getName());
        request.setAttribute("startTime", new Date().getTime());
        //如果返回false，后续的控制器方法不会执行，
        return true;
    }

    //控制器方法执行之后被调用，如果有异常就不会调用这个方法
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("控制器方法执行后，无异常");
        long start = (long) request.getAttribute("startTime");
        System.out.println("拦截器耗时：" + (new Date().getTime() - start));
    }

    //不管抛不抛异常，这个方法都会被调用
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("控制器方法执行后，无论如何都执行");
        long start = (long) request.getAttribute("startTime");
        System.out.println("拦截器耗时：" + (new Date().getTime() - start));
        //Ex是异常对象
    }
}
//拦截器更进了一步，可以知道具体的控制器方法和异常对象
//但是拿不到传递给控制器方法的值，只能从请求里拿东西
//就要用到最强力的切片了，切片还是Spring 的核心功能，很多地方都会用到