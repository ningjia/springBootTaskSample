package com.example.springBootTest.task;

import com.example.springBootTest.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试同步任务
 * doTaskOne、doTaskTwo、doTaskThree三个函数，顺序的执行完成
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class SyncTaskTest {
    @Autowired
    private SyncTask syncTask;

    @Test
    public void test() throws Exception {
        syncTask.doTaskOne();
        syncTask.doTaskTwo();
        syncTask.doTaskThree();
    }
}
