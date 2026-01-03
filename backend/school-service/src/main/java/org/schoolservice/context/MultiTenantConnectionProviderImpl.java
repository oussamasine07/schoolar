package org.schoolservice.context;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class MultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    private static final Logger logger = LoggerFactory.getLogger( MultiTenantConnectionProviderImpl.class );
    private final DataSource dataSource;

    public MultiTenantConnectionProviderImpl ( final DataSource dataSource ) {
        this.dataSource = dataSource;
    }

    @Override
    protected DataSource selectAnyDataSource() {
        return dataSource;
    }

    @Override
    protected DataSource selectDataSource(Object o) {
        return dataSource;
    }

    public Connection getAnyConnection( Object tenentID ) throws SQLException {
        String tenent = tenentID != null ? tenentID.toString() : "public";
        logger.info("connecting with schema " + tenent);

        Connection con = getAnyConnection();

        try (Statement stmt = con.createStatement()) {
            stmt.execute("set search_path to " + tenent);
        }

        return con;
    }

    public void releaseConnection (Object tenentId, Connection conn) throws SQLException {

        try ( Statement stmt = conn.createStatement() ) {
            stmt.execute("set search_path to public");
        }

        releaseAnyConnection( conn );
    }
}






















