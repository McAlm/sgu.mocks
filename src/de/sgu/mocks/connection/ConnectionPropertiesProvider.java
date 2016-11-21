package de.sgu.mocks.connection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public interface ConnectionPropertiesProvider {

    Properties getConnectionProps(String mall) throws IOException;

}
