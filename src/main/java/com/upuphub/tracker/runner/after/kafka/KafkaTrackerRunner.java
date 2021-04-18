package com.upuphub.tracker.runner.after.kafka;

import java.util.Random;

import com.upuphub.tracker.BehaviorData;
import com.upuphub.tracker.runner.after.AbstractTrackerRunner;



/**
 * Kafka Tracker Handler 实现
 *
 * @author Inspiration S.P.A Leo
 **/
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
