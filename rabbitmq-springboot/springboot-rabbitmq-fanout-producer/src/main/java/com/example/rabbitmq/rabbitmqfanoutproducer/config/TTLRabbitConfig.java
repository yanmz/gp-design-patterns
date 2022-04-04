package com.example.rabbitmq.rabbitmqfanoutproducer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class TTLRabbitConfig {
    @Bean
    public Queue emailTtlQueue() {
        //针对队列设置过期时间
        Map<String,Object> agrs = new HashMap();
        agrs.put("x-message-ttl",5000);
        agrs.put("x-dead-letter-exchange","dead_exchange");
        agrs.put("x-dead-letter-routing-key","dead");
        return new Queue("email.ttl.queue", true,false,false,agrs);
    }

    @Bean
    public Queue emailTtlMessageQueue() {
        return new Queue("email.ttl.message.queue", true);
    }

    @Bean
    public DirectExchange  directTtlOrderExchange() {
        return new DirectExchange("ttl_order_exchange", true, false);
    }

    @Bean
    public DirectExchange  directTtlMessageOrderExchange() {
        return new DirectExchange("ttl_Message_exchange", true, false);
    }

    @Bean
    public Binding bindingTtl() {
        return BindingBuilder.bind(emailTtlQueue()).to(directTtlOrderExchange()).with("ttl");
    }

    @Bean
    public Binding bindingTtl2() {
        return BindingBuilder.bind(emailTtlMessageQueue()).to(directTtlMessageOrderExchange()).with("ttl-message");
    }
}