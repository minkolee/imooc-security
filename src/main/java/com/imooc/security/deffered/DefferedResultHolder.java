package com.imooc.security.deffered;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 传递消息的对象
 */
@Component
public class DefferedResultHolder {


    //把String认为是订单号,DefferedResult认为是处理结果

    private Map<String, DefferedResult<String>> map = new HashMap<>();

    public Map<String, DefferedResult<String>> getMap() {
        return map;
    }

    public void setMap(Map<String, DefferedResult<String>> map) {
        this.map = map;
    }
}
