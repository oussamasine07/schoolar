package org.schoolservice.kafka.producers;

import org.schoolservice.kafka.events.SchoolCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SchoolCreatedProducer {
    private final KafkaTemplate<String, SchoolCreatedEvent> schoolCreated;

    public SchoolCreatedProducer(KafkaTemplate<String, SchoolCreatedEvent> schoolCreated) {
        this.schoolCreated = schoolCreated;
    }

    public void onSchoolCreated (SchoolCreatedEvent schoolCreatedEvent) {
        schoolCreated.send("school.created", schoolCreatedEvent);
    }
}
