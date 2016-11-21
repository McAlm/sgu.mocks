package de.sgu.mocks.connection;

import java.sql.Connection;
import java.util.Properties;

public class ConnectionProviderImpl implements ConnectionProvider {

    private ConnectionPropertiesProvider connectionPropertiesProvider;
    private DatabaseConnectionProvider databaseConnectionProvider;

    public ConnectionProviderImpl(ConnectionPropertiesProvider connectionPropertiesProvider, DatabaseConnectionProvider databaseConnectionProvider) {
        this.connectionPropertiesProvider = connectionPropertiesProvider;
        this.databaseConnectionProvider = databaseConnectionProvider;
    }

    @Override
    public Connection getConnection(String mall) throws ConnectionException {
        Properties connectionProps = this.connectionPropertiesProvider.getConnectionProps(mall);
        return this.databaseConnectionProvider.createConnection(connectionProps);
    }

}
