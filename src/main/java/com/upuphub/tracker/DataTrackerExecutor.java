package com.upuphub.tracker;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 异步执行器
 *
 * @author Inspiration S.P.A Leo
 **/
public class DataTrackerExecutor {

    /**
     * 异步执行线程池 使用线性无界阻塞队列,并提供核心线程 最大线程以及存活时间的配置
     */
    private final ExecutorService trackerExecutor;

    /**
     * 标识执行线程序号
     */
    private final AtomicInteger runIndex = new AtomicInteger();

    /**
     * 初始化异步打点工具的异步执行器
     *
     * Creates a new {@code ThreadPoolExecutor} with the given initial
     * parameters and default rejected execution handler.
     *
     * @param corePoolSize the number of threads to keep in the pool, even
     *        if they are idle, unless {@code allowCoreThreadTimeOut} is set
     * @param maximumPoolSize the maximum number of threads to allow in the
     *        pool
     * @param keepAliveTime when the number of threads is greater than
     *        the core, this is the maximum time that excess idle threads
     *        will wait for new tasks before terminating.
     *        creates a new thread
     * @throws IllegalArgumentException if one of the following holds:<br>
     *         {@code corePoolSize < 0}<br>
     *         {@code keepAliveTime < 0}<br>
     *         {@code maximumPoolSize <= 0}<br>
     *         {@code maximumPoolSize < corePoolSize}
     * @throws NullPointerException if {@code workQueue}
     *         or {@code threadFactory} is null
     */
    public DataTrackerExecutor(int corePoolSize,
                                  int maximumPoolSize,
                                  long keepAliveTime) {
        this.trackerExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(), new DataTrackThreadFactory(runIndex){

        });
    }

    /**
     * 异步执行runnable方法
     *
     * @param runnable 需要运行的可运行对象
     */
    public void runAsync(Runnable runnable){
        trackerExecutor.submit(runnable);
    }

    /**
     * 同步执行的runnable方法
     *
     * @param runnable 需要运行的可运行对象
     */
    public void run(Runnable runnable){
       runnable.run();
    }


    /**
     * 线程创建工厂主要用户重命名线程执行名称
     */
    abstract static class DataTrackThreadFactory implements ThreadFactory {
        /**
         * 线程数的index数据
         */
        private final AtomicInteger runIndex;

        public DataTrackThreadFactory(AtomicInteger runIndexIn) {
            this.runIndex = runIndexIn;
        }

        @Override
        public Thread newThread(Runnable run) {
            Thread thread =  new Thread(run, String.format("tracker-thread-%d", runIndex.getAndIncrement()));
            thread.setDaemon(false);
            return thread;
        }
    }
}
