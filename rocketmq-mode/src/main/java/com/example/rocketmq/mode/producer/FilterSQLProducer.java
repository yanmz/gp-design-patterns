package com.example.rocketmq.mode.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

public class FilterSQLProducer {
    public static void main(String[] args)  throws  Exception{
        //1.创建消息生产者producer，并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("group1");

        //2.指定Nameserver地址
        producer.setNamesrvAddr("192.168.50.132:9876;192.168.50.129:9876");

        producer.start();
        for (int i = 0; i < 10; i++) {
            Message msg = new Message("FilterSQLTopic","Tag1",("Hello,Hello"+i).getBytes());
            msg.putUserProperty("i",String.valueOf(i));

            SendResult result = producer.send(msg);

            System.out.println("发送结果："+result);
            //线程睡1秒
            TimeUnit.SECONDS.sleep(1);
        }
        producer.shutdown();
    }
}
