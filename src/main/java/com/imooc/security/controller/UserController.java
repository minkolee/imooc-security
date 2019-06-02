package com.imooc.security.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.security.domain.User;
import com.imooc.security.exhandler.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    //RequestParam可以设置不传，以及默认值，原来不知道
    public List<User> query(@RequestParam(required = false, defaultValue = "jenny") String username) {
        System.out.println(username);
        List<User> users = new ArrayList<>();
        users.add(new User("jenny","gugugu"));
        users.add(new User("cony","saner"));
        users.add(new User("minko", "gugugu"));
        return users;
    }

    //RM可以用正则表达式替代字符串匹配，写在变量的括号里
    @GetMapping("/{id:\\d+}")
    // 3 给方法设置上对应的视图
    // 注意，使用了@JsonView之后，那些没有加JsonView的字段都不会被展示
    @JsonView(User.UserDetailView.class)
    public User queryDetail(@PathVariable String id) {

//        throw new UserNotExistException(id);

        return new User("tom", "gugugu");
    }

    @PostMapping
    @JsonView(User.UserSimpleView.class)
    public User createUser(@Valid @RequestBody User user) {
        System.out.println("POST响应接受");
        System.out.println(user);
//        if(errors.hasErrors()){
//            errors.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
//        }
        User newUser = new User(user.getUsername(), user.getPassword());
        newUser.setId("1");
        return newUser;
    }

    @PutMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User update(@Valid @RequestBody User user, BindingResult errors) {
        User target = new User();
        if (errors.hasErrors()) {
            //打印所有的错误信息
            errors.getAllErrors().forEach(error ->{
                //设置了字段上的自定义错误信息，就不用强转了，可以直接打印错误信息
//                FieldError fieldError = (FieldError) error;
//                System.out.println(fieldError.getField() + " " + fieldError.getDefaultMessage());
                System.out.println(error.getDefaultMessage());
            });

        } else {
            target.setId(user.getId());
            target.setUsername(user.getUsername());
            target.setPassword(user.getPassword());
            target.setBirthday(user.getBirthday());
        }

        System.out.println("目标是:" + target);
        return target;
    }

    //不打算返回响应体的话，直接使用void返回类型
    @DeleteMapping("/{id:\\d+}")
    @ResponseStatus(code= HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
    }

}























