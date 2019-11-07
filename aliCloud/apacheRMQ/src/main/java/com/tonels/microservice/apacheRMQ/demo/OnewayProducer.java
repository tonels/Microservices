package com.tonels.microservice.apacheRMQ.demo;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.com.tonels.microservicemon.message.Message;
import org.apache.rocketmq.remoting.com.tonels.microservicemon.RemotingHelper;

public class OnewayProducer {
    public static void main(String[] args) throws Exception{
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("OnewayProducer_group");
        // Specify name server addresses.
        producer.setNamesrvAddr("192.168.1.79:9876");

        //Launch the instance.
        producer.start();
        for (int i = 0; i < 10; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("AsyncTopic" /* Topic */,
                    "TagA" /* Tag */,
                    ("OnewayProducer 生产消息 " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            producer.sendOneway(msg);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}
