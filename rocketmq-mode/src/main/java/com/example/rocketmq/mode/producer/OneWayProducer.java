package com.example.rocketmq.mode.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * 发送单向消息
 */
public class OneWayProducer {
    public static void main(String[] args) throws Exception {
        //        1.创建消息生产者producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("group1");
//        2.指定Nameserver地址
        producer.setNamesrvAddr("192.168.50.133:9876;192.168.50.134:9876");
//        3.启动producer
        producer.start();
//        4.创建消息对象，指定主题Topic、Tag和消息体
        for (int i = 0; i < 10; i++) {
            Message msg = new Message("TopicTest", "Tag3", ("Hello,Rocket" + i).getBytes());
//            5.发送消息
            producer.sendOneway(msg);

            TimeUnit.SECONDS.sleep(1);
        }
//        6.关闭生产者producer
        producer.shutdown();
    }
}
