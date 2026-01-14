package org.studentservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.studentservice.model.CustomMessage;

import javax.sql.DataSource;

@Service
public class KafkaMessageConsumerService {

    private final FlywayMigrationService flywayMigrationService;
    private final DataSource dataSource;

    public KafkaMessageConsumerService(
            FlywayMigrationService flywayMigrationService,
            DataSource dataSource
    ) {
        this.flywayMigrationService = flywayMigrationService;
        this.dataSource = dataSource;
    }


    @KafkaListener(topics = "school.created", groupId = "student-service")
    public void listenMigration(CustomMessage msg) {
        System.out.println("migrating all tables, inside " + msg.getMessage());
        flywayMigrationService.migrate(dataSource, msg.getMessage());
        System.out.println("entire schema migrated");
    }

}
