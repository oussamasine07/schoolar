package org.schoolservice.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class SchemaService {

    private final EntityManager entityManager;

    public SchemaService (
            final EntityManager entityManager
    ) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void createSchema ( String schemaName ) {
        if (!schemaName.matches("[a-zA-Z0-9_]+")) {
            throw new IllegalArgumentException("invalid schema name");
        }

        entityManager
                .createNativeQuery("CREATE SCHEMA IF NOT EXISTS " + schemaName)
                .executeUpdate();
    }

}


















