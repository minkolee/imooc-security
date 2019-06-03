package com.imooc.security.async;


import com.imooc.security.deffered.DefferedResult;
import com.imooc.security.deffered.DefferedResultHolder;
import com.imooc.security.deffered.MockQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefferedController {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DefferedResultHolder defferedResultHolder;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/dorder")
    public DefferedResult<String> dor() throws Exception {
        logger.info("队列下单开始");

        //随机订单号
        String orderNumber = "fdsjkjfd";
        //放到队列中,一秒钟之后有结果
        mockQueue.setPlaceOrder(orderNumber);

        DefferedResult<String> result = new DefferedResult<>();


        defferedResultHolder.getMap().put(orderNumber, result);

        logger.info("队列下单结束");
        return result;
    }

}
