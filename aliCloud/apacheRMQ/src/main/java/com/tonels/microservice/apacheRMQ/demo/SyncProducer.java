package com.tonels.microservice.apacheRMQ.demo;

import com.tonels.microservice.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.com.tonels.microservicemon.message.Message;
import org.apache.rocketmq.remoting.com.tonels.microservicemon.RemotingHelper;

public class SyncProducer {
    public static void main(String[] args) throws Exception {

        // 定义 生产者的组,一般一个broker对应一个 group,也可以多个broker指定同一个Group
//        DefaultMQProducer producer = new DefaultMQProducer("group-1");
        DefaultMQProducer producer = new DefaultMQProducer("SyncGroup");
        // NameServer 地址
        producer.setNamesrvAddr("192.168.1.79:9876");
        // 加载生产者实例
        producer.start();
        // 创建一个消息(Topic,Tag,Body[]),消息内容是bytes数组形式传输
//        Message msg = new Message("BenchmarkTest","TagA",("消息 body").getBytes(RemotingHelper.DEFAULT_CHARSET));

        // 这里测试到，如果这里指定了服务端不存在的 Topic,会有如下异常
        // No route info of this topic, TL

//        producer.createTopic("accessKey","TL",2); // 可创建Topic,指定分片 queue num

        Message msg = new Message("BenchmarkTest","TagA",("消息 body").getBytes(RemotingHelper.DEFAULT_CHARSET));

        //调用broker,生产消息
        SendResult sendResult = producer.send(msg);
        System.out.println("生产消息反馈: "+ sendResult.getSendStatus());

        // 生产完毕，shutdown
        producer.shutdown();
    }
}







