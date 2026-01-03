package org.schoolservice.config;


import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.schoolservice.context.CurrentTenantIdentifierResolverImpl;
import org.schoolservice.context.MultiTenantConnectionProviderImpl;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class HibernateConfig {

    private final JpaProperties jpaProperties;

    public HibernateConfig ( final JpaProperties jpaProperties ) {
        this.jpaProperties = jpaProperties;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter () {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public CurrentTenantIdentifierResolver currentTenantIdentifierResolver () {
        return new CurrentTenantIdentifierResolverImpl();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory (
            DataSource dataSource,
            MultiTenantConnectionProviderImpl multiTenantConnectionProvider,
            CurrentTenantIdentifierResolver currentTenantIdentifierResolver
    ) {

        Map<String, Object> jpaPropertiesMap = new HashMap<>(jpaProperties.getProperties());
        jpaPropertiesMap.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
        jpaPropertiesMap.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource( dataSource );
        em.setPackagesToScan("org.schoolservice.model");
        em.setJpaVendorAdapter( jpaVendorAdapter() );
        em.setJpaPropertyMap( jpaPropertiesMap );

        return em;
    }

}




























