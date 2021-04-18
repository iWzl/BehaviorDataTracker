package com.upuphub.tracker.factory;

import com.upuphub.tracker.DataTrackerExecutor;
import com.upuphub.tracker.logging.Logger;
import com.upuphub.tracker.logging.LoggerFactory;

/**
 *  数据打点工具的异步执行器的创建获取工厂
 *
 * @author Inspiration S.P.A Leo
 **/
public abstract class DataTrackerExecutorFactory {

    /**
     * 私有化构造器
     */
    private DataTrackerExecutorFactory() {
    }

    /**
     * 日志打印工具对象实例
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DataTrackerExecutorFactory.class);

    /**
     * 打点执行点的执行器
     */
    private static DataTrackerExecutor dataTrackerExecutor;

    /**
     * 获取数据打点工具的异步执行器
     *
     * @return 数据打点工具的异步执行器
     */
    public static DataTrackerExecutor getDataTrackerExecutor(){
        return dataTrackerExecutor;
    }

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
    public static void initDataTrackerExecutor(
            int corePoolSize, int maximumPoolSize, long keepAliveTime){
        if (null == dataTrackerExecutor){
            synchronized (DataTrackerExecutorFactory.class){
                if (null == dataTrackerExecutor){
                    DataTrackerExecutorFactory.dataTrackerExecutor =
                            new DataTrackerExecutor(corePoolSize, maximumPoolSize, keepAliveTime);
                    if (LOGGER.isDebugEnabled()){
                        LOGGER.debug("initialize the DataTrackerExecutor successfully {} {} {}",
                                corePoolSize, maximumPoolSize, keepAliveTime);
                    }
                }
            }
        }else {
            LOGGER.warn("repeatedly initialize the DataTrackerExecutor");
        }
    }

}
