package com.example.springBootTest.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 修改默认线程池的配置
 *
 * 注意：该线程池被所有的异步任务共享，而不属于某一个异步任务
 */
@Configuration
//@EnableAsync
public class TaskExecutePool implements AsyncConfigurer {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TaskPoolConfig taskPoolConfig;//配置属性类

    @Override
    public Executor getAsyncExecutor() {
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

    /**
     * 异步任务中的异常处理
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable arg0, Method arg1, Object... arg2) {
                logger.error("=========================="+arg0.getMessage()+"=======================", arg0);
                logger.error("exception method:"+arg1.getName());
            }
        };
    }
}
