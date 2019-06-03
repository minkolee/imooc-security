package com.imooc.security.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;


/**
 * 异步处理REST请求
 */
@RestController
public class AsycnController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/order")
    public String order() throws Exception {

        logger.info("主线程开始");

        Thread.sleep(1000);

        logger.info("主线程返回");
        return "success";
    }

    @RequestMapping("/aorder")
    public Callable<String> aorder() throws Exception {
        logger.info("异步线程开始");

        Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("副线程开始");
                Thread.sleep(1000);

                logger.info("副线程结束");
                return "success";
            }
        };

        logger.info("异步线程结束");
        return result;
    }

}

//普通order情况下：
//线程只睡的时候，看日志，是nio-8060-exec-1这个线程执行的，前后基本上间隔了一秒

//2019-06-03 10:32:15.675  INFO 18084 --- [nio-8060-exec-1] c.imooc.security.async.AsycnController   : 主线程开始
//2019-06-03 10:32:16.678  INFO 18084 --- [nio-8060-exec-1] c.imooc.security.async.AsycnController   : 主线程返回

//asyncorder情况下
//2019-06-03 10:36:27.903  INFO 19264 --- [nio-8060-exec-1] c.imooc.security.async.AsycnController   : 异步线程开始
//2019-06-03 10:36:27.904  INFO 19264 --- [nio-8060-exec-1] c.imooc.security.async.AsycnController   : 异步线程结束
//2019-06-03 10:36:27.923  INFO 19264 --- [         task-1] c.imooc.security.async.AsycnController   : 副线程开始
//2019-06-03 10:36:28.924  INFO 19264 --- [         task-1] c.imooc.security.async.AsycnController   : 副线程结束
// 有两个线程，主线程先返回了，副线程睡了一秒
//浏览器那边还是1秒，因为要等待结果
//但是这个时候的主线程可以做其他事情，等callable处理完毕，再返回响应
//提高了并发
//这种情况下，异步代码直接写在业务中
//很多情况下，两个业务是独立的，通过消息队列进行通信，所以还需要知道消息队列。