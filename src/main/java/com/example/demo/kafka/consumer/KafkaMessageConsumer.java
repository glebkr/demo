package com.example.demo.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageConsumer {

    @KafkaListener(topics = "topic", groupId = "group")
    public void getMessageFromTopic(String message) {
        System.out.println("Message: " + message);
    }

}
