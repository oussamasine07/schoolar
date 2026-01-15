package org.studentservice.kafka.consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.studentservice.kafka.events.SchoolCreatedEvent;
import org.studentservice.service.FlywayMigrationService;

import javax.sql.DataSource;

@Service
public class SchoolCreatedConsumer {

    private final FlywayMigrationService flywayMigrationService;
    private final DataSource dataSource;

    public SchoolCreatedConsumer(
            FlywayMigrationService flywayMigrationService,
            DataSource dataSource
    ) {
        this.flywayMigrationService = flywayMigrationService;
        this.dataSource = dataSource;
    }


    @KafkaListener(topics = "school.created", groupId = "student-service")
    public void listen(SchoolCreatedEvent school ) {
        System.out.println("migrating all tables, inside " + school.getTenentId() + " Schema");
        flywayMigrationService.migrate(dataSource, school.getTenentId());
        System.out.println("entire schema migrated");
    }

}
