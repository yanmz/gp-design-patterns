package com.example.rabbitmq.rabbitmqfanoutproducer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeadRabbitConfig {
    @Bean
    public Queue deadQueue() {
        return new Queue("dead.queue", true);
    }

    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange("dead_exchange", true, false);
    }


    @Bean
    public Binding bindingTtlDead() {
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with("dead");
    }
}