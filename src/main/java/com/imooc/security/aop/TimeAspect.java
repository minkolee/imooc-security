package com.imooc.security.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
public class TimeAspect {


    //@Before在方法执行前调用，类似于拦截器的prehandle，还有after(正常执行之后才执行)，afterthrough（不管有无异常），around（前三种的综合）
    @Around("execution(* com.imooc.security.controller.UserController.*(..))")
    //参数是一个JoinPoint类型，是拦截住的方法的相关信息
    public Object handleControllerMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("Aspect start");

        //获取方法所有的参数
        Object[] args = proceedingJoinPoint.getArgs();

        System.out.println(Arrays.toString(args));

        long start = new Date().getTime();
        Object result = proceedingJoinPoint.proceed();

        System.out.println("执行时间是：" + (new Date().getTime() - start));

        System.out.println("Aspect ends");

        return result;
    }
}
