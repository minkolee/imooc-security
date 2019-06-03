package com.imooc.security.deffered;


import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
//contextrefreshedevent是Spring 容器初始化完成的事件
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DefferedResultHolder defferedResultHolder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {


        //用一个新线程去监听队列,有变化就设置完成订单的内容
        new Thread(() -> {
            while (true) {
                if (StringUtils.isNotBlank(mockQueue.getCompleteOrder())) {
                    String orderNumber = mockQueue.getCompleteOrder();
                    logger.info("返回订单处理结果: " + orderNumber);
                    //向holder中的结果对象中写入订单号,表示处理完成
                    defferedResultHolder.getMap().get(orderNumber).setOrder(orderNumber);
                    //将队列设置为空
                    mockQueue.setCompleteOrder(null);

                } else {
                    try {
                        Thread.sleep(100);
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                }
            }
        }).start();


    }
}
















