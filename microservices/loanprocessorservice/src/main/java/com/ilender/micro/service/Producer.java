package com.ilender.micro.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "test-topic";
    private static final String TOPIC2 = "test-topic-2";

    @Autowired(required=true)
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(Object messageText) {
        logger.info(String.format("#### -> Producing message -> %s", messageText));

        Message<Object> message = MessageBuilder
                .withPayload(messageText)
                .setHeader(KafkaHeaders.TOPIC, TOPIC)
                .build();

        this.kafkaTemplate.send(message);
    }

    public void sendMessage2(Object messageText) {
        logger.info(String.format("#### -> Producing message2222 -> %s", messageText));

        Message<Object> message = MessageBuilder
                .withPayload(messageText)
                .setHeader(KafkaHeaders.TOPIC, TOPIC2)
                .build();

        this.kafkaTemplate.send(message);
    }
}