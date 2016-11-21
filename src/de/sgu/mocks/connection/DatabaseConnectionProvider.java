package de.sgu.mocks.connection;

import java.sql.Connection;
import java.util.Properties;

public interface DatabaseConnectionProvider {

    Connection createConnection(Properties props);

}
