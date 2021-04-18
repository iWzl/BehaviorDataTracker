package com.upuphub.tracker.config;

import java.lang.management.ManagementFactory;
/**
 * 通用配置属性
 *
 * @author Inspiration S.P.A Leo
 **/
public final class CommonConfig {

    /**
     * 私有话类的构造器
     */
    private CommonConfig() {
    }

    /**
     * 默认的未知参数定义
     */
    public static final String UNKNOWN = "UNKNOWN";

    /**
     * CGLIB_CLASS_FLAG
     */
    public static final String CGLIB_CLASS_FLAG = "$$";

    /**
     * 服务主机信息的全局静态常量，格式为PID@HostName
     */
    public static final String SERVER_HOST_NAME = getServerHostName();

    /**
     * 获取全局的JVM运行参数
     *
     * @return 服务主机信息的全局静态常量，格式为PID@HostName
     */
    public static String getServerHostName(){
        return ManagementFactory.getRuntimeMXBean().getName();
    }
}
