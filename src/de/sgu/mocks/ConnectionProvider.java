package de.sgu.mocks;

import java.sql.Connection;

public interface ConnectionProvider {

    Connection getConnection(String string);

}
