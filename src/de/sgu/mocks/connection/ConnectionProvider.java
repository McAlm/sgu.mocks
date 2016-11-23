package de.sgu.mocks.connection;

import java.io.IOException;
import java.sql.Connection;

public interface ConnectionProvider {

    Connection getConnection(String string) throws ConnectionException, IOException;

}
