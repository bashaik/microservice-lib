
package com.jshop.cloud.orm;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl
{
	private static final long serialVersionUID = -2551153254188901684L;
	
	@Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Override
    protected DataSource selectAnyDataSource()
    {
        return dataSource;
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier)
    {
        return dataSource;
    }

    @Override
    public Connection getAnyConnection() throws SQLException
    {
        return selectAnyDataSource().getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException
    {
        connection.close();
    }

    @Override
    public Connection getConnection(String schemaIdentifier) throws SQLException
    {

        final Connection connection = getAnyConnection();
        try
        {
            connection.createStatement().execute("USE " + schemaIdentifier);
        }
        catch (SQLException e)
        {
            releaseConnection(schemaIdentifier, connection);
            throw new HibernateException("Error switching connection to schema [" + schemaIdentifier + "]", e);
        }
        return connection;
    }

    @Override
    public void releaseConnection(String schemaIdentifier, Connection connection) throws SQLException
    {
        try
        {
            connection.createStatement().execute("USE " + schemaIdentifier);
        }
        catch (SQLException e)
        {
            throw new HibernateException("Error switching connection to schema [" + schemaIdentifier + "]", e);
        }
        connection.close();
    }

}
