package com.imooc.security.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.security.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
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
        return new User("tom", "gugugu");
    }

    @PostMapping
    @JsonView(User.UserSimpleView.class)
    public User createUser(@RequestBody User user) {
        System.out.println(user);
        User newUser = new User(user.getUsername(), user.getPassword());
        newUser.setId("1");
        return newUser;
    }

}























