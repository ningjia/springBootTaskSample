package com.example.springBootTest.task;

import com.example.springBootTest.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.Future;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class AsyncTaskTest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AsyncTask asyncTask;

    /**
     * 测试多个任务的异步调用过程
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        long start = System.currentTimeMillis();
        //调用异步任务
        Future<String> task1 = asyncTask.doTaskOne();
        Future<String> task2 = asyncTask.doTaskTwo();
        Future<String> task3 = asyncTask.doTaskThree();
        //循环判断所有任务是否已完成，用于计算总时间
        while(true) {
            if(task1.isDone() && task2.isDone() && task3.isDone()) {
                // 三个任务都调用完成，退出循环
                break;
            }
            Thread.sleep(1000);
        }
        long end = System.currentTimeMillis();
        logger.info("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }

    /**
     * 测试异步调用过程的参数传递及结果返回
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        long start = System.currentTimeMillis();
        //调用异步任务
        Future<String> task4 = asyncTask.doTaskFour("测试参数");
        String result = task4.get();
        //输出处理结果
        long end = System.currentTimeMillis();
        logger.info("任务全部完成，总耗时：" + (end - start) + "毫秒，处理结果："+result);
    }
}
