package com.example.rabbitmq.rabbitmqfanoutproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * RabbitMQ用于分布式事务处理
 * -- 生产者发送MQ
 * 1.生产者发送消息到RabbitMQ中，同时生产者保存消息到冗余表
 * 2.消息发送到MQ成功后返回ACK修改冗余表状态
 * 3.如果消息发送MQ不成功 则定时调度重试发送MQ
 * -- 消费者消费MQ
 * 1.消费者正常消费消息 #正常执行
 * 2.消费者消费消息失败，  #不正常执行
 * 2.1 解决方法：
 * 可以通过消息重试，设置重试次数  #不可取
 * 可以通过try catch +手动ack方式+死信队列+人工干预  #可取
 * 可以通过try catch +手动ack方式 #不可取 数据丢失
 */
@SpringBootApplication
public class SpringbootRabbitmqFanoutProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRabbitmqFanoutProducerApplication.class, args);
    }

}
