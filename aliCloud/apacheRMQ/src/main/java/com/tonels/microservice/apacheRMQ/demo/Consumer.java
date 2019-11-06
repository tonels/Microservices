package com.tonels.microservice.apacheRMQ.demo;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.com.tonels.microservicemon.message.MessageExt;

import java.util.List;

public class Consumer {

    public static void main(String[] args) throws InterruptedException, MQClientException {

        // 实例消费者 Group
//        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group-1");
//        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("SyncGroup");
//        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("OnewayProducer_group");
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("AsyncProducerGroup");

        // 指定NameServer地址
        consumer.setNamesrvAddr("192.168.1.79:9876");

        // 订阅Topic下，指定Tag,或者 所有的
//        consumer.subscribe("BenchmarkTest", "*");
//        consumer.subscribe("AsyncTopic", "*");
        consumer.subscribe("AsyncTopic", "*");
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
//                String s = Arrays.toString(msgs);
                System.out.printf("%s 接收的消息: %n", msgs.get(0));
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        //Launch the consumer instance.
        consumer.start();

        System.out.printf("Consumer Started.");
    }
}