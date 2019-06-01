package com.imooc.security.exhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//这个注解表示该控制器用于处理其他控制器中出现的异常
@ControllerAdvice
public class ControllerExceptionHandler {


    //这个注解用于处理对应的异常，这里就是针对UserNotExistException异常
    @ExceptionHandler(UserNotExistException.class)
    //加上了注解之后，这个方法的参数就是对应的异常对象
    // @ResponseBody 表示将Controller返回的结果写入响应体，而不是像普通视图一样进行视图解析
    //一般用在这种特殊的控制类中，因为不能直接标记上RestController
    @ResponseBody
    //可以自行设置一个响应码，该错误还是得抛出错误
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleUserNotExistException(UserNotExistException ex) {
        Map<String, Object> result = new HashMap<>();
        result.put("id", ex.getId());
        result.put("message", ex.getMessage());
        return result;
    }
}
