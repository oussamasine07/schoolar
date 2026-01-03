package org.schoolservice.context;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.schoolservice.filter.AppTenentContext;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver<String> {
    @Override
    public String resolveCurrentTenantIdentifier() {
        String tenent = AppTenentContext.getCurrentTenent();
        System.out.println("Resolving TENENT ID: " + tenent);
        return Objects.requireNonNullElse( tenent, "public" );
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
