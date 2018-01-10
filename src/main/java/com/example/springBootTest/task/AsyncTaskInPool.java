package com.example.springBootTest.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AsyncTaskInPool {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static Random random =new Random();

    @Async("myAsynTaskcPool")  //myTaskAsynPool即配置线程池的方法名，此处如果不写自定义线程池的方法名，会使用默认的线程池
    public void doTask1(int i) throws InterruptedException{
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(1000));
        long end = System.currentTimeMillis();
        logger.info("Task"+i+" 完成，耗时：" + (end - start) + "毫秒");
    }

}
