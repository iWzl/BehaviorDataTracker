package com.upuphub.tracker;

import com.upuphub.tracker.factory.DataTrackerExecutorFactory;
import com.upuphub.tracker.factory.TrackerHandlerFactory;
import com.upuphub.tracker.intercept.AspectjMethodInvocation;
import com.upuphub.tracker.intercept.Invocation;
import com.upuphub.tracker.runner.TrackerHandlerRunner;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * User Behavior Data Tracker
 *
 * @author Inspiration S.P.A Leo
 **/
@Aspect
public class BehaviorDataTracker {


    /**
     * AOP切入所有的带有DataPoint注解的点
     */
    @Pointcut("@annotation(com.upuphub.tracker.annotation.DataPoint)")
    public void trackDataPointCut() {
    }

    /**
     * 环绕通知切入点,增强切入点属性
     *
     * @param joinPoint 切入点
     * @return 原切入点的返回
     * @throws Throwable 切入执行可能的返回
     */
    @Around("trackDataPointCut()")
    public Object triggerDataTrackingPointHandler(ProceedingJoinPoint joinPoint) throws Throwable {
        Invocation invocation  = new AspectjMethodInvocation(joinPoint);
        return TrackerHandlerRunner.runTracker(invocation);
    }



    public static Builder newBuilder(){
        return new Builder();
    }

    public static class Builder {
        /**
         * 异步执行线程池的核心线程数量
         */
        private int corePoolSize = 2;

        /**
         * 异步执行线程池的最大执行线程数量
         */
        private int maximumPoolSize = 8;

        /**
         * 异步执行线程的存活回收时间
         */
        private long keepAliveTime =  300;

        private String[] packageNames;


        public int getCorePoolSize() {
            return corePoolSize;
        }

        public Builder setCorePoolSize(int corePoolSizeIn) {
            this.corePoolSize = corePoolSizeIn;
            return this;
        }

        public int getMaximumPoolSize() {
            return maximumPoolSize;
        }

        public Builder setMaximumPoolSize(int maximumPoolSizeIn) {
            this.maximumPoolSize = maximumPoolSizeIn;
            return this;
        }

        public long getKeepAliveTime() {
            return keepAliveTime;
        }

        public Builder setKeepAliveTime(long keepAliveTimeIn) {
            this.keepAliveTime = keepAliveTimeIn;
            return this;
        }

        public String[] getPackageNames() {
            return packageNames;
        }

        public Builder setPackageNames(String[] packageNamesIn) {
            this.packageNames = packageNamesIn;
            return this;
        }

        public BehaviorDataTracker builder(){
            TrackerHandlerFactory.initTrackerHandler(this.packageNames);
            DataTrackerExecutorFactory.initDataTrackerExecutor(this.corePoolSize,
                    this.maximumPoolSize, this.keepAliveTime);
            return new BehaviorDataTracker();
        }
    }
}
