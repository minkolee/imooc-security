package com.imooc.security.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//第一个泛型是注解，第二个是要验证的注解类型，这里是String，只能放在String Field上
//设置成Object可以放在任意Field上
public class MyValidator implements ConstraintValidator<MyValid, String> {

    //可以注入容器中的任何东西来校验逻辑

    //这里是校验逻辑，返回true表示成功，返回false表示失败
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("被注解字段传入的值是：" + s);
        return (s.startsWith("jenny"));
    }


    //初始化要做的工作
    @Override
    public void initialize(MyValid constraintAnnotation) {
        System.out.println("自定义验证器启动了");
    }
}
