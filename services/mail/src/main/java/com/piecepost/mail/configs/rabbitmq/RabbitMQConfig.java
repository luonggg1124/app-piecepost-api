package com.piecepost.mail.configs.rabbitmq;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.queueName}")
    private String queueName;

    @Bean
    public Queue mail(){
        return new Queue(queueName,true);
    }
}
