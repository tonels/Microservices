package com.tonels.microservice.apacheRMQ.demo;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.com.tonels.microservicemon.message.Message;
import org.apache.rocketmq.remoting.com.tonels.microservicemon.RemotingHelper;

public class AsyncProducer {
    public static void main(String[] args) throws Exception {

        // 实例 Group
        DefaultMQProducer producer = new DefaultMQProducer("AsyncProducerGroup");

        // 指定NameServer地址
        producer.setNamesrvAddr("192.168.1.79:9876");

        // 加载、启动实例
        producer.start();

        //设置自动创建topic的key值
        producer.setCreateTopicKey("AUTO_CREATE_TOPIC_KEY");
        // 重试次数
        producer.setRetryTimesWhenSendAsyncFailed(0);

        for (int i = 0; i < 10; i++) {
            final int index = i;
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("AsyncTopic", "TagA", "OrderID188", "HellrldT".getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
                }
                @Override
                public void onException(Throwable e) {
                    System.out.printf("%-10d Exception %s %n", index, e);
                    e.printStackTrace();
                }
            });
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}
