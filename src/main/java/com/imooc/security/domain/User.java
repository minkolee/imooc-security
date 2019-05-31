package com.imooc.security.domain;

import com.fasterxml.jackson.annotation.JsonView;

public class User {

    /**
     * @JsonView 使用接口来声明 多个视图
     * 然后在给domain的每个field上标注，指定视图
     * 最后给方法上指定即可。
     * <p>
     * 这样方法就会实际展示指定的字段
     */


    // 1 创建接口视图
    public interface UserSimpleView {
    }

    public interface UserDetailView extends UserSimpleView {
        /**
         * 有了继承关系，用户详情视图会显示用户简单视图中的所有字段
         */
    }


    // 2 给字段加上视图
    @JsonView(UserSimpleView.class)
    private String username;

    @JsonView(UserDetailView.class)
    private String password;

    @JsonView(UserSimpleView.class)
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {

    }
}
