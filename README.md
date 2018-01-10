# SpringBoot中的线程管理
## 说明
- SyncTaskTest：非异步的线程测试。
    - test()：doTaskOne、doTaskTwo、doTaskThree三个函数按顺序执行在主线程中。
- AsyncTaskTest：异步的线程测试。
    - test()：doTaskOne、doTaskTwo、doTaskThree三个函数，同时异步运行，各自运行在独立的线程中。
    - test2()：通过独立线程执行doTaskFour函数，且实现参数传入与结果返回。
- AsyncTaskInPoolTest：基于线程池的异步线程测试
    - AsyncTaskTest()：基于线程池管理，测试异步线程。（线程池配置文件：taskPool.properties）
## 原理解释
### 当一个任务被添加到线程池时：
1. 如果此时线程池中的数量小于corePoolSize，即使线程池中的线程都处于空闲状态,也要创建新的线程来处理被添加的任务。
2. 如果此时线程池中的数量等于 corePoolSize，但是缓冲队列 workQueue未满，那么任务被放入缓冲队列。
3. 如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量小于maximumPoolSize，建新的线程来处理被添加的任务。
4. 如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量等于maximumPoolSize，那么通过 handler所指定的策略来处理此任务。
5. 也就是：处理任务的优先级为：核心线程corePoolSize、任务队列workQueue、最大线程 maximumPoolSize，如果三者都满了，使用handler处理被拒绝的任务。
6. 当线程池中的线程数量大于 corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止。这样，线程池可以动态的调整池中的线程数。
### 两种情况会拒绝处理任务：
1. 当线程数已经达到maxPoolSize，切队列已满，会拒绝新任务。
2. 当线程池被调用shutdown()后，会等待线程池里的任务执行完毕，再shutdown。如果在调用shutdown()和线程池真正shutdown之间提交任务，会拒绝新任务。
### 线程池会调用rejectedExecutionHandler来处理拒绝的任务。如果没有设置默认是AbortPolicy，会抛出异常。
#### ThreadPoolExecutor类有几个内部实现类来处理这类情况：
- AbortPolicy 丢弃任务，抛运行时异常。
- CallerRunsPolicy 用调用者所在线程来运行任务。
- DiscardPolicy 不处理，丢弃掉。
- DiscardOldestPolicy 从队列中踢出最先进入队列（最后一个执行）的任务，并执行当前任务。
- 实现RejectedExecutionHandler接口，可自定义处理器。

## Refer
- [Spring Boot中使用@Async实现异步调用](http://blog.didispace.com/springbootasync/)
- [spring boot-执行Async任务时，使用自定义的线程池](http://blog.csdn.net/liuchuanhong1/article/details/64132520)
- [线程池优雅停机(应用停止时的线程池处理)](http://blog.sina.com.cn/s/blog_7d1968e20102x1x4.html)