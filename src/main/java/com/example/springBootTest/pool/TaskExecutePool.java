package com.example.springBootTest.pool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
//@EnableAsync
public class TaskExecutePool {

    @Autowired
    private TaskPoolConfig taskPoolConfig;

    @Bean
    public Executor myAsynTaskcPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(taskPoolConfig.getCorePoolSize());
        executor.setMaxPoolSize(taskPoolConfig.getMaxPoolSize());
        executor.setQueueCapacity(taskPoolConfig.getQueueCapacity());
        executor.setKeepAliveSeconds(taskPoolConfig.getKeepAliveSeconds());
        executor.setThreadNamePrefix(taskPoolConfig.getThreadNamePrefix());

//        executor.setWaitForTasksToCompleteOnShutdown(true);//（默认为false），表明等待所有线程执行完
//        executor.setAwaitTerminationSeconds(60 * 15);// 等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());//当pool已经达到max size的时候，如何处理新任务
        executor.initialize();
        return executor;
    }

}
