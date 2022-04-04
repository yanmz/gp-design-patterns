package com.example.rabbitmq.rabbitmqfanoutproducer;

import com.example.rabbitmq.rabbitmqfanoutproducer.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootRabbitmqFanoutProducerApplicationTests {

    @Autowired
    OrderService orderService;
    @Test
    public void contextLoads() throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            Long userId = 100L + i;
            Long productId = 10001L + i;
            int num = 10;
            orderService.makeOrder(userId, productId, num);
        }
    }

    @Test
    public void contextLoadsDirect() throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            Long userId = 100L + i;
            Long productId = 10001L + i;
            int num = 10;
            orderService.makeOrderDirect(userId, productId, num);
        }
    }
}
