package com.imooc.security.exhandler;

public class UserNotExistException extends RuntimeException {

    private static final long serialVersionUID = -21378921789984L;

    public UserNotExistException(String id) {
        super("用户不存在，自定义的错误类, id是 " + id);
        this.id = id;
    }


    //自定义的部分
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
