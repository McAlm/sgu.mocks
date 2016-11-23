package de.sgu.mocks.connection;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

import org.easymock.EasyMock;
import org.junit.Test;

public class ConnectionProviderTest {

    private Connection conn;
    private ConnectionPropertiesProvider connectionPropertiesProvider;

    
    
    @Test
    public void testGetConnectionReturnsConnection() throws ConnectionException, IOException {
        
        connectionPropertiesProvider = EasyMock.createMock(ConnectionPropertiesProvider.class);
        Properties props = new Properties();
        props.put("host", "192.186.1.200");
        props.put("user", "testuser");
        props.put("password", "testpassword");
        EasyMock.expect(connectionPropertiesProvider.getConnectionProps("aeon")).andReturn(props);
        
        
        DatabaseConnectionProvider databaseConnectionProvider = EasyMock.createMock(DatabaseConnectionProvider.class);
        Connection connection = EasyMock.createMock(Connection.class);
        EasyMock.expect(databaseConnectionProvider.createConnection(props)).andReturn(connection);
        
        ConnectionProvider cp = new ConnectionProviderImpl(connectionPropertiesProvider, databaseConnectionProvider);
        EasyMock.replay(connectionPropertiesProvider, databaseConnectionProvider);
        
        conn = cp.getConnection("aeon");
        
        EasyMock.verify(connectionPropertiesProvider, databaseConnectionProvider);
        
        assertNotNull(conn);

    }

}
