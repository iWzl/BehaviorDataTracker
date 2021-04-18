# 有部分倾入性的用户行为追踪记录实现工具

![用户行为追踪记录实现工具](https://img.hellobyebye.com/doc/2021041517152716184781271618478127031FuqqxV.png)

## 概要

### 背景 

为了解决当下后台项目中，对用户行为(接口和方法)的行为调用追踪，在尽可能对项目没有侵入性的完成方法级的方法(接口)调用数据打点工具。完成部分用户行为数据的追踪和记录，通过Kafka、RocketMQ或其他的消息中间件推送到系统统一的数据仓库中,便于对用户的数据和行为进行分析和相关整理。

### 功能

由于做用户行为分析，需要收集的处理的数据主要由5个部分

- 谁(用户uin)
- 在什么时候(接口调用时间/接口调用完成时间)
- 执行什么事件(标识事件、请求入参、返回参数、异常参数)
- 在什么地方执行(服务名称、当前服务运行的PID)
- 执行操作执行了多少时间

当前工具主要实现和功能

- [x] 对Spring容器管理的Bean的动态代理，对调用的入参,返回，异常的数据收集
- [x] 对非Spring容器管理的Bean的动态代理，对调用的入参,返回，异常的数据收集
- [x] 对用户执行事件的标识和数据收集、包括Topic、Event、执行用时和执行的服务信息
- [x] 方法调用中对'谁'的信息收集和处理器实现
- [x] 对收集消息的可重载,可配置实现
- [x] 多种日志框架的兼容和统一包装
- [x] 行为数据收集完成后对数据输出的多方式支持和兼容
- [x] 对信息处理器和信息发送器的异步化执行支持
- [ ] 对Kafka的发送器处理器的实现
- [ ] RocketMQ的发送器处理器的实现
- [x] 对日志发送处理器的实现
- [x] 简化数据点的注解属性配置
- [ ] 项目中的JavaDoc文档
- [ ] 代码结构符合公司代码编码规范
- [ ] 代码Review
- [x] 性能和功能测试

## 快速使用

### 项目拉取

项目放在Github中,首先拉取项目到本地
> https://inspirationgit.onluxy.com/backend/behavior-data-tracker

```shell
git clone git@inspirationgit.onluxy.com:backend/behavior-data-tracker.git
git clone https://inspirationgit.onluxy.com/backend/behavior-data-tracker.git
```

完成项目拉取后,在项目根目录执行

```shell
mvn clean install
```

将项目打包到自己本地的仓库中

### 项目引入

```xml
<dependency>
    <groupId>com.upuphub.tracker</groupId>
    <artifactId>Behavior-Data-Tracker</artifactId>
    <version>${latest-version}</version>
</dependency>
```

### 项目配置

#### Sping Framework项目

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">
    <!-- AOP自动代理 -->
    <aop:aspectj-autoproxy proxy-target-class="true"/>
    <!-- 配置初始化打点处理器 -->
    <bean class="com.upuphub.tools.handler.DataTrackPointHandler" init-method="initDataTrackPointHandler">
        <property name="handlerConfigLocation" value="com.inspiration.account.track"/>
        <property name="corePoolSize" value="5"/>
        <property name="keepAliveTime" value="0"/>
        <property name="maximumPoolSize" value="10"/>
    </bean>
    <!-- 打点处和执行处都需要交给Spring容器管理 -->
</beans>

```

#### SpringBoot项目

```java
@Configuration
public class TrackerConfig {
    @Bean
    public BehaviorDataTracker initBehaviorDataTracker(){
        return BehaviorDataTracker.newBuilder()
                .setPackageNames(new String[]{TrackerDemoApplication.class.getPackage().getName()})
                .builder();
    }
}
```

### 使用示例

#### 打点标志位

```java
@DataPoints(topic = "DemoTopic",type = RunnerType.TO_LOG,handlerClazz = DemoHandler.class)
@RestController
public class DemoController {
    @Autowired
    private DemoSpringService demoSpringService;
    private final DemoNoSpringService demoNoSpringService;
    private final CommandProc<?,?> commandProc;

    public DemoController() {
        demoNoSpringService = TrackerProxyBeanFactory.buildProxyWrapBeanInstance(DemoNoSpringService.class);
        commandProc = TrackerProxyBeanFactory.buildProxyWrapBeanInstance(SyncProfileReqCommandProc.class);
    }

      @RequestMapping(value = "/demo/1",consumes = MediaType.APPLICATION_JSON_VALUE)
    @DataPoint(event = "World", handlerMethod = "demoHandler")
    public ResultData trackUserBehaviorWithJsonSpring2(@RequestBody EnterData enterData) {
        return demoSpringService.getSpringResultData(enterData);
    }
  
      @RequestMapping("/demo/2")
    public ResultData trackUserBehaviorWithNoSpring(@RequestBody EnterData enterData) {
        return demoNoSpringService.getNoSpringResultData(enterData);
    }
}
```

```java
public class DemoNoSpringService {

    @DataPoint(topic = "Hello",event = "World",type = RunnerType.TO_LOG,handlerClazz = DemoHandler.class,handlerMethod = "demoHandler")
    public ResultData getNoSpringResultData(EnterData enterData){
        resultData.setStringData("DemoNoSpringService");
      // 省略部分
      resultData.setListData(Collections.singletonList("DemoNoSpringService"));
        return resultData;
    }
}
```

#### 信息处理器部分

```java
@Handler
public class DemoHandler {

    @HandlerMethod(alias = "demoHandler",type = ResultType.ID,async = true)
    public Long simpleDemoHandler(@Return ResultData resultData,Long test) throws InterruptedException {
        Thread.sleep(5000);
        return 123345L;
    }

    @HandlerMethod(alias = "commandProcHandler",type = ResultType.BEHAVIOR_DATA,async = false)
    public BehaviorData syncProfileReqCommandProcHandler(@Input(index = 0) Msg msg,
                                                         @Return Object returnObject) {
        BehaviorData behaviorData = new BehaviorData();
        behaviorData.setId(msg.getHead().getImei());
        behaviorData.setEnter(new String[]{toJson(msg)});
        if(returnObject instanceof Message){
            behaviorData.setResult(toJson((Message) returnObject));
        }else {
            behaviorData.setResult("XXXXXXXXXXXx");
        }
        return behaviorData;
    }

    public static String toJson(Message sourceMessage){
        return new JsonFormat().printToString(sourceMessage);
    }
}
```

## 使用说明

### 注解

#### @DataPoint

方法注解,用于标记和声明用户追踪数据的数据来源点,主要包含

| 属性参数      | 类型       | 说明                 | 备注                                        |
| ------------- | ---------- | -------------------- | ------------------------------------------- |
| topic         | String     | 数据点包含的主题     | 必选参数、可通过@DataPoints简化该配置       |
| event         | String     | 数据点对应的参数事件 | 必填参数、不可简化和省略                    |
| type          | RunnerType | 事件数据的输出位置   | 可选、默认为日志打印、可通过@DataPoints简化 |
| handlerClazz  | Class      | 数据处理器的类       | 可选参数、可通过@DataPoints简化             |
| handlerMethod | String     | 数据处理的方法       | 可选参数、默认为数据点方法名                |

> Tips:RunnerType的定义对应打点读取到的数据输出发送器的实现:
>
> - [x] TO_LOG: 对应LoggerTrackerRunner，将读取到的数据通过日志输出,默认
> - [ ] TO_KAFKA: 对应KafkaTrackerRunner，将读取到的数据通过Kafka发送
> - [ ] TO_ROCKETMQ:对应RocketMQTrackerRunner,将读取到的通过RocketMQ发送(预设未实现)
> - [ ] TO_HANDLER:将读取到的数据通过自己实现的方式进行处理、未实现
> - [x] TO_NONE:不对读取到的数据进行任何处理

#### @DataPoints

类注解,用户简化@DataPoint注解的反对属性，主要简化

| 属性参数     | 类型       | 说明               | 备注                                 |
| ------------ | ---------- | ------------------ | ------------------------------------ |
| topic        | String     | 数据点包含的主题   | 必选参数,可被DataPoint配置的参数覆盖 |
| type         | RunnerType | 事件数据的输出位置 | 必选参数,可被DataPoint配置的参数覆盖 |
| handlerClazz | Class      | 数据处理器的类     | 必选参数,可被DataPoint配置的参数覆盖 |

> Tips:@DataPoints注解主要用与简化@DataPoint的配置、会被@DataPoint非默认参数覆盖

#### @Handler

类注解,表示该类是数据处理器，该类会被本工具通过反射创建并且统一维护,Handler不会注册到Spring容器中。其类下标记了@HandlerMethod注解的方法会与@Datapoint注解关联，在该方法调用时，通过反射完成处理调用

#### @HandlerMethod

方法注解，表示该方法是数据处理器的方法，其依托于@Handler注解标识的类,主要包含

| 属性参数 | 类型       | 说明                         | 备注                                                         |
| -------- | ---------- | ---------------------------- | ------------------------------------------------------------ |
| alias    | String     | 用与标识HandlerMethod的别名  | 与@DataPoint调用方的的HandlerMethod名称一致                  |
| type     | ResultType | 表示该方法的返回类型         | 参考ResultType的说明                                         |
| async    | bool       | 标识该方法是同步还是异步执行 | 同步调用可能会影响原方法的调用处理时间,异步调用能减少对原方法调用处理的影响，但无法获取到原方法处理的上下文环境 |

> Tips:ResultType处理对应了数据处理器的几种数据返回类型:
>
> | 枚举值                      | 说明                                                         | 备注                                            |
> | --------------------------- | :----------------------------------------------------------- | ----------------------------------------------- |
> | ID                          | 仅处理行为数据的ID(UIN)、其余数据默认方法直接在工具中拼接填写 | 返回类型只能是String或者是Number类型,其余会报错 |
> | BEHAVIOR_DATA               | 返回和拼接用户的行为属性属性,当部分拼接返回的数据为空时,会使用工具中默认的拼接补全。 | 返回类型只能是BehaviorData其余会报错            |
> | BEHAVIOR_DATA_OVERALL       | 返回和拼接用户的行为属性属性,当部分拼接返回的数据为空时,不会使用工具中默认的拼接补全、始终与拼接的数据为准。 | 返回类型只能是BehaviorData其余会报错            |
> | BEHAVIOR_DATA_EVENT         | 返回和拼接用户的行为属性属性，并覆盖原有注解协定的Topic和Event事件,返回的部分行为数据的值为空或默认值时,会使用默认的工具拼接方法进行补全。 | 返回类型只能是BehaviorDataEvent其余会报错       |
> | BEHAVIOR_DATA_EVENT_OVERALL | 返回和拼接用户的行为属性属性，并覆盖原有注解协定的Topic和Event事件,返回的部分行为数据的值为空或默认值时,不会使用默认的工具拼接方法进行补全。推送给发射器的属性将会以改返回的属性为准。 | 返回类型只能是BehaviorDataEvent其余会报错       |

#### HandlerMethod入参信息组

当使用参数处理器时,工具能够通过注解拿到数据调用点方法的请求参数、返回产生和异常参数的、分别对应以下3个注解

##### @Input

参数注解,标记在被@HandlerMethod标记的方法中，用于获取原方法调用的请求参数、其需要index的必选参数,用于说明是原数据点方法的第几个输入参数、数字从0开始。

##### @Return

参数注解,标记在被@HandlerMethod标记的方法中,用于获取原调用方法的返回参数。

##### @Exception

参数注解,标记在被@HandlerMethod标记的方法中,用于获取原调用方法的异常返回参数

> Tips: HandlerMethod入参信息组的注解当前版本没有进行产生的类型判断,在使用时需要确保handlerMethod的产生接收类型与数据点方法的产生类型一致，否者可能会出现类的类型一致的异常，是后期的优化点之一。

### 数据输出的说明

#### BehaviorData

该对象是实际获取到的打点数据的获取到所有数据的载体属性，其主要包含7个关键属性字段。

```java
public class BehaviorData implements Serializable {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("id")
    private String id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("timestamp")
    private Long timestamp;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("server")
    private String server;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("enter")
    private Object[] enter;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("result")
    private Object result;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("throwable")
    private Throwable throwable;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("useTime")
    private int use;
}
```



| 属性      | 类型      | 说明                                               | 备注                                                         |
| --------- | --------- | -------------------------------------------------- | ------------------------------------------------------------ |
| id        | String    | 标记该行为所属对象                                 | 一般来说对系统来说是系统用户的uin,不可能为空,未设置或未知是其值的No-Set |
| timestamp | Long      | 原处理方法处理完成的时间(数据采取点的信息发送时间) | UTC时间戳、毫秒级，不是原处理方法的开始时间，不可能为空      |
| server    | String    | 该事件发生机器机器                                 | 包含机器名称和进程的PID,不可能为空                           |
| enter     | arrays    | 原数据点方法的入参                                 | 标记了数据点方法的单个/多个输入参数、可能为空                |
| result    | object    | 原数据点方法的返回参数                             | 标记了数据点方法的唯一返回参数、可能为空                     |
| throwable | Throwable | 原数据点方法执行发送异常的产生属性                 | 标记了数据点方法的唯一异常属性，一般为空                     |
| use       | int       | 记录原方法执行的调用用时                           | 单位为毫秒、可以用timestamp-use较为精确的获取到原数据点的调用时间 |

#### BehaviorDataEvent

在数据处理器中需要对行为数据信息更加只有的数据调整时间,能够通过改对象对行为数据对象进行Topic、Event在内的更加详细的调整。

```java
public class BehaviorDataEvent {
    private String topic;
    private String event;
    private BehaviorData behaviorData;
}
```

| 属性         | 类型         | 说明                         | 备注                                           |
| ------------ | ------------ | ---------------------------- | ---------------------------------------------- |
| topic        | String       | 数据点包含的主题             | 必选参数、@DataPoint配置，这里设置后会对其覆盖 |
| event        | String       | 数据点对应的参数事件         | 必选参数、@DataPoint配置，这里设置后会对其覆盖 |
| behaviorData | BehaviorData | 从数据点获取到的行为数据属性 | 详细见BehaviorData                             |

#### 工具初始化配置

待完善..............

## 测试报告

该测试报告主要测试工具的正常基本运行情况和对原有项目的影响

测试使用的接口

```java
    @RequestMapping(value = "/datapoint",consumes = MediaType.APPLICATION_JSON_VALUE)
    @DataPoint(event = "World", handlerMethod = "demoHandler")
    public ResultData datapoint(@RequestBody EnterData enterData) {
        ResultData resultData = new ResultData();
        resultData.setDoubleData(123123.213);
        resultData.setLongData(3424324324324313431L);
        resultData.setFloatData(231.231F);
        resultData.setMapData(Collections.singletonMap("Hello", "World"));
        resultData.setStringData("AAAAAAAAAAAAA");
        resultData.setListData(Collections.singletonList("LLLLLLLLLLLL"));
        return resultData;
    }

    @RequestMapping(value = "/nodatapoint",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultData nodatapoint(@RequestBody EnterData enterData) {
        ResultData resultData = new ResultData();
        resultData.setDoubleData(123123.213);
        resultData.setLongData(3424324324324313431L);
        resultData.setFloatData(231.231F);
        resultData.setMapData(Collections.singletonMap("Hello", "World"));
        resultData.setStringData("AAAAAAAAAAAAA");
        resultData.setListData(Collections.singletonList("LLLLLLLLLLLL"));
        return resultData;
    }
```

数据处理器

```java
    @HandlerMethod(alias = "demoHandler",type = ResultType.ID)
    public Long simpleDemoHandler(@Return ResultData resultData) throws InterruptedException {
        // 这里执行随机睡个0-100ms
        Random random = new Random();
        Thread.sleep(random.nextInt(100));
        return 123345L;
    }
```

Kafka消费端

```java
public class KafkaTrackerRunner extends AbstractTrackerRunner {

    public KafkaTrackerRunner(String topic, String event, BehaviorData behaviorData) {
        super(topic, event, behaviorData);
    }

    @Override
    public void handler(String topic, String event, BehaviorData behaviorData) {
        // 这里执行随机睡个0-100ms
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

```

### T6 C2000

![image-20210415155652603](https://img.hellobyebye.com/doc/2021041515565716184734171618473417922NiJagN.png)

![image-20210415155713399](https://img.hellobyebye.com/doc/2021041515571816184734381618473438796jVM9B0.png)

```shell
❯ wrk -t6 -c2000 -d120s --script=wrk.lua --latency http://localhost:8080/nodatapoint

Running 2m test @ http://localhost:8080/nodatapoint
  6 threads and 2000 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     8.77ms   18.15ms 864.68ms   99.24%
    Req/Sec     4.67k     1.42k    8.84k    73.95%
  Latency Distribution
     50%    7.37ms
     75%    8.92ms
     90%   10.84ms
     99%   18.73ms
  3274487 requests in 2.00m, 0.92GB read
  Socket errors: connect 1753, read 138, write 0, timeout 0
Requests/sec:  27263.18
Transfer/sec:      7.88MB
```

![image-20210415163434672](https://img.hellobyebye.com/doc/2021041516343916184756791618475679697YV73i7.png)

```shell
❯ wrk -t6 -c2000 -d120s --script=wrk.lua --latency http://localhost:8080/datapoint
Running 2m test @ http://localhost:8080/datapoint
  6 threads and 2000 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    12.44ms   54.64ms   1.67s    98.17%
    Req/Sec     4.45k     1.76k   10.03k    69.98%
  Latency Distribution
     50%    7.31ms
     75%    8.53ms
     90%   10.33ms
     99%  159.82ms
  2508199 requests in 2.00m, 725.23MB read
  Socket errors: connect 1753, read 148, write 0, timeout 980
Requests/sec:  20864.49
Transfer/sec:      6.03MB
```

![image-20210415160236577](https://img.hellobyebye.com/doc/2021041516024216184737621618473762136GBgudL.png)

```shell
❯ wrk -t6 -c2000 -d120s --script=wrk.lua --latency http://localhost:8080/datapoint

Running 2m test @ http://localhost:8080/datapoint
  6 threads and 2000 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    11.22ms   45.49ms   1.30s    98.12%
    Req/Sec     4.68k     2.07k   15.37k    67.85%
  Latency Distribution
     50%    6.80ms
     75%    7.89ms
     90%    9.38ms
     99%  156.47ms
  2575507 requests in 2.00m, 744.69MB read
  Socket errors: connect 1753, read 125, write 0, timeout 1470
Requests/sec:  21450.02
Transfer/sec:      6.20MB
```

### T6 C1500

![image-20210415160110761](https://img.hellobyebye.com/doc/2021041516011716184736771618473677429QoBCHS.png)

```shell
❯ wrk -t6 -c1500 -d120s --script=wrk.lua --latency http://localhost:8080/nodatapoint
Running 2m test @ http://localhost:8080/nodatapoint
  6 threads and 1500 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     6.07ms    3.23ms 126.07ms   93.58%
    Req/Sec     4.47k     2.86k   10.21k    62.42%
  Latency Distribution
     50%    5.77ms
     75%    6.79ms
     90%    7.99ms
     99%   11.08ms
  230261 requests in 2.00m, 66.58MB read
  Socket errors: connect 1255, read 151, write 0, timeout 3430
Requests/sec:   1917.60
Transfer/sec:    567.76KB
```

![image-20210415163850092](https://img.hellobyebye.com/doc/2021041516385416184759341618475934622sp0Ahx.png)

```shell
❯ wrk -t6 -c1500 -d120s --script=wrk.lua --latency http://localhost:8080/datapoint
Running 2m test @ http://localhost:8080/datapoint
  6 threads and 1500 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     7.69ms   15.59ms 392.66ms   99.32%
    Req/Sec     4.86k     2.11k    9.63k    71.55%
  Latency Distribution
     50%    6.37ms
     75%    7.41ms
     90%    8.96ms
     99%   14.92ms
  834536 requests in 2.00m, 241.30MB read
  Socket errors: connect 1255, read 149, write 0, timeout 3185
Requests/sec:   6950.55
Transfer/sec:      2.01MB
```

### T6 C1000

![image-20210415162321180](https://img.hellobyebye.com/doc/2021041516232816184750081618475008021831jUE.png)

![image-20210415162417458](https://img.hellobyebye.com/doc/2021041516242316184750631618475063306DhLb7X.png)

```shell
❯ wrk -t6 -c1000 -d120s --script=wrk.lua --latency http://localhost:8080/nodatapoint

Running 2m test @ http://localhost:8080/nodatapoint
  6 threads and 1000 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     5.96ms    2.92ms 125.14ms   92.88%
    Req/Sec     4.33k     2.99k   10.37k    58.25%
  Latency Distribution
     50%    5.66ms
     75%    6.53ms
     90%    7.73ms
     99%   10.75ms
  245639 requests in 2.00m, 71.02MB read
  Socket errors: connect 751, read 137, write 0, timeout 4165
Requests/sec:   2045.61
Transfer/sec:    605.66KB
```

![image-20210415161813977](https://img.hellobyebye.com/doc/2021041516182016184747001618474700278j07o7N.png)

![image-20210415161950056](https://img.hellobyebye.com/doc/2021041516195516184747951618474795407hR66jm.png)

```shell
❯ wrk -t6 -c1000 -d120s --script=wrk.lua --latency http://localhost:8080/datapoint

Running 2m test @ http://localhost:8080/datapoint
  6 threads and 1000 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency     9.45ms   39.23ms   1.16s    99.07%
    Req/Sec     4.54k     2.19k    9.49k    67.31%
  Latency Distribution
     50%    6.60ms
     75%    7.69ms
     90%    9.25ms
     99%   25.02ms
  551483 requests in 2.00m, 159.46MB read
  Socket errors: connect 751, read 155, write 0, timeout 3185
Requests/sec:   4598.86
Transfer/sec:      1.33MB
```

### 压力测试总结

与Springboot较为简单的接口返回比较95%的请求用时基本没有影响，当压力机线程在6个1500个连接数的情况下，99%的请求用时基本没有影响，在带有数据点的接口由于整体多了0-200ms的线程睡眠，并且测试环境本地线程复杂，初步看来埋点工具对于原始项目接口的性能影响不大。

## 项目结构

```shell
.
├── BehaviorData.java #见BehaviorData定义
├── BehaviorDataEvent.java #见BehaviorDataEvent定义 
├── BehaviorDataTracker.java #数据打点工具的启动和初始化入口
├── DataTrackerExecutor.java #数据工具的执行线程池
├── annotation #工具项目中定义的所有的注解
├── config #工具项目的静态文件定义
├── exception #工具项目文件定义的异常属性
├── factory #工具项目需要的Handler相关的工厂类
├── handler #默认的数据处理Handler实现
├── intercept #工具实现的动态代理
├── lang #工具定义的基本属性配置定义
├── logging #基于Mybatis的日志整合实现
├── proxy #工具实现的动态代理实际的代理对象
├── runner #工具的核心运行器,包含数据处理器和数据发送器的实现
├── serializer #数据状态的序列化器属性对象
└── utils #工具项目需要的工具

```

### 核心执行类方法

```java
public class TrackerHandlerRunner {
    private static final Logger logger = LoggerFactory.getLogger(TrackerHandlerRunner.class);

    public static Object runTracker(Invocation methodInvocation) throws Throwable {
        Exception runException = null;
        Object resultObject = null;
        long startTime = System.currentTimeMillis();
        int use = 0;
        // 这里执行原方法,记录时间和返回参数以及可能出现的异常
        try {
            resultObject = methodInvocation.proceed();
        } catch (Exception ex) {
            runException = ex;
        }finally {
            use = (int) (System.currentTimeMillis() - startTime);
        }
        try {
            Method dataPointMethod = methodInvocation.getTargetMethod();
            DataPoints dataPoints = TrackerHandlerFactory.getDataPointFromLazyCacheByClazz(
                    methodInvocation.getTargetObject().getClass());
            DataPoint dataPoint = dataPointMethod.getAnnotation(DataPoint.class);
            if (null == dataPoint) {
                return methodInvocation.proceed();
            }
            DataPointDefinition dataPointDefinition = buildDataPointDefinition(
                    dataPoint,dataPoints,dataPointMethod.getName());
            // 完成行为数据的处理
            TrackerHandlerRunner.runBehaviorDataHandler(
                    dataPointDefinition,methodInvocation,resultObject,runException,use);
            // 当原方法调用异常,这里对原异常进行原样抛出,不进行相关处理
        }catch (Exception ex){
            logger.error("TrackerHandlerRunner Handler Error",ex);
        }
        if(null != runException){
            throw runException;
        }
        return resultObject;
    }
}

```

### 核心时序图

![image-20210415171839853](https://img.hellobyebye.com/doc/2021041517184616184783261618478326552J2yaDC.png)