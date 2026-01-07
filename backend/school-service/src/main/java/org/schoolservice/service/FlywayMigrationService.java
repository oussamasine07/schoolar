package org.schoolservice.service;

import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class FlywayMigrationService {

    public Flyway migrate (DataSource dataSource, String schema) {
        Flyway fl = Flyway.configure()
                .outOfOrder( true )
                .baselineOnMigrate( true )
                .locations("classpath:db/migration/tenent")
                .schemas(schema)
                .table("flyway_schema_history")
                .dataSource(dataSource)
                .load();

        fl.migrate();
        return fl;
    }

}
