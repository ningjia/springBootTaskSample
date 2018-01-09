package com.example.springBootTest.task;

import com.example.springBootTest.Application;
import com.example.springBootTest.task.AsyncTaskInPool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutionException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class AsyncTaskInPoolTest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AsyncTaskInPool asyncTaskPool;

    /**
     * 通过线程池，测试异步任务
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void AsyncTaskTest() throws InterruptedException, ExecutionException {

        for (int i = 0; i < 100; i++) {
            asyncTaskPool.doTask1(i);
        }

        logger.info("All tasks finished.");
    }

}
