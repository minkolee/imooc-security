package com.imooc.security.deffered;

//包含一个字符串的结果,再传回来

public class DefferedResult<T> {

    private T order;

    public DefferedResult(T order) {
        this.order = order;
    }

    public DefferedResult() {

    }

    public T getOrder() {
        return order;
    }

    public void setOrder(T order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "DefferedResult{" +
                "order=" + order +
                '}';
    }
}
