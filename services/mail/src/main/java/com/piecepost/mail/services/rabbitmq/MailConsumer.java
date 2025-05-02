package com.piecepost.mail.services.rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piecepost.mail.https.requests.email.EmailRequest;
import com.piecepost.mail.services.mail.MailService;

@Service
@EnableRabbit
public class MailConsumer {
    private final MailService mailService;
    public MailConsumer(MailService mailService) {
        this.mailService = mailService;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queueName}")
    public void receive(String json) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        EmailRequest request = mapper.readValue(json, EmailRequest.class);
        mailService.sendMail(request.getTo(), request.getSubject(), request.getBody());
    }
}
