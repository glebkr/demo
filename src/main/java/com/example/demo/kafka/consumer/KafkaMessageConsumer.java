package com.example.demo.kafka.consumer;

import com.example.demo.person.model.Person;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageConsumer {

    @KafkaListener(topics = "topic", groupId = "group")
    public void getMessageFromTopic(Person person) {
        System.out.println("Message: " + person.toString());
    }

}
