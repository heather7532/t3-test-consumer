package org.alpenlogic.tools.examples.consumer.listener;

import org.apache.avro.generic.GenericRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListenerService {

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(GenericRecord message) {
        // Process the Avro message
        System.out.println("Received message: " + message);
    }
}