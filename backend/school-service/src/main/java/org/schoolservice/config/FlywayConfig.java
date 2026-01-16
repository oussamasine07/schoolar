package org.schoolservice.config;


import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;

@Configuration
@ConditionalOnProperty(prefix = "spring", name = "flyway.enabled", matchIfMissing = true)
public class FlywayConfig {

    private final boolean outOfOrder;
    private final boolean baseLineOnMigrate;

    public FlywayConfig (
            @Value("${spring.flyway.out-of-order:false}") boolean outOfOrder,
            @Value("${spring.flyway.baseline-on-migrate:false}") boolean baseLineOnMigrate
    ) {
        this.outOfOrder = outOfOrder;
        this.baseLineOnMigrate = baseLineOnMigrate;
    }

    @Bean
//    @DependsOn("dataSource")
    public Flyway flyway(DataSource dataSource) {
        Flyway fl = Flyway.configure()
                .outOfOrder(outOfOrder)
//                .baselineOnMigrate(baseLineOnMigrate)
                .locations("classpath:db/migration/default")
                .schemas("public")
                .dataSource(dataSource)
                .load();

        fl.migrate();
        return fl;
    }

}

























