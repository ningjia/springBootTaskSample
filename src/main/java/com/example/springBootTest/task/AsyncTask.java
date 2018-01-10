package com.example.springBootTest.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * 异步任务
 */
@Component
public class AsyncTask {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static Random random =new Random();

    @Async
    public Future<String> doTaskOne() throws Exception {
        logger.info("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        logger.info("完成任务一，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务一完成");
    }

    @Async
    public Future<String> doTaskTwo() throws Exception {
        logger.info("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        logger.info("完成任务二，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务二完成");
    }

    @Async
    public Future<String> doTaskThree() throws Exception {
        logger.info("开始做任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        logger.info("完成任务三，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务三完成");
    }

    @Async
    public Future<String> doTaskFour(String prams) throws Exception {
        logger.info("开始做任务四，参数="+prams);
        long start = System.currentTimeMillis();
        Future<String> future;
        try {
            Thread.sleep(random.nextInt(10000));
            future = new AsyncResult<>("任务四的回传参数="+prams);
        } catch (InterruptedException e) {
            future = new AsyncResult<String>("error");
        }
        long end = System.currentTimeMillis();
        logger.info("完成任务四，耗时：" + (end - start) + "毫秒");
        return future;
    }

}
