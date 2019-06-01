package com.imooc.security.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
//当前的注解用哪个类去注解，需要在那个类里使用方法返回校验结果
@Constraint(validatedBy = MyValidator.class)

public @interface MyValid {
    //默认错误信息
    String message() default "密码必须以jenny开头";

    //和Hibernate相关的验证属性，暂时用不到
    Class<?>[] groups() default {};

    //和Hibernate相关的验证属性，暂时用不到
    Class<? extends Payload>[] payload() default {};
}
