package com.example.rocketmq.mode.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BatchProducer {
    public static void main(String[] args)  throws Exception{
        //1.创建消息生产者producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        //2.指定Nameserver地址
        producer.setNamesrvAddr("192.168.50.132:9876;192.168.50.129:9876");
        //3.启动producer
        producer.start();
        //构建消息集合
        List<Message> msgs = new ArrayList<Message>();

        //4.创建消息对象，指定主题Topic、Tag和消息体
        /**
         * 参数一：消息主题Topic
         * 参数二：消息Tag
         * 参数三：消息内容
         */
        Message message = new Message("BatchTopic", "Tag", "Hello world 0".getBytes());
        Message message1 = new Message("BatchTopic", "Tag", "Hello world 1".getBytes());
        Message message2 = new Message("BatchTopic", "Tag", "Hello world 2".getBytes());

        msgs.add(message);
        msgs.add(message1);
        msgs.add(message2);

        //5.发送消息
        SendResult result = producer.send(msgs);

        //发送状态
        SendStatus status = result.getSendStatus();

        System.out.println("发送结果:" + result);

        //线程睡1秒
        TimeUnit.SECONDS.sleep(1);

        producer.shutdown();
    }
}
