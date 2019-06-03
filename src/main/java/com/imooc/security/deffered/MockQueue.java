package com.imooc.security.deffered;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 不实际配置队列，模拟一个队列，收到消息之后延迟一秒
 */

@Component
public class MockQueue {

    private Logger logger = LoggerFactory.getLogger(getClass());


    //下单消息
    private String placeOrder;

    //订单完成消息
    private String completeOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) throws Exception {

        new Thread(() -> {
            logger.info("队列接到下单请求:");
            try {
                Thread.sleep(1000);
            } catch (Exception ex) {
                System.out.println(ex);
            }
            this.completeOrder = placeOrder;
            logger.info("队列下单完成");
        }).start();

    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
