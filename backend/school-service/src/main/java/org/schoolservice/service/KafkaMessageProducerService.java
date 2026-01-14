package org.schoolservice.service;

import org.schoolservice.model.CustomMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageProducerService {

    private final KafkaTemplate<String, CustomMessage> kafkaTemplate;

    public KafkaMessageProducerService(KafkaTemplate<String, CustomMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void trigerMigrations (CustomMessage msg) {
        kafkaTemplate.send("school.created", msg);
    }

}













