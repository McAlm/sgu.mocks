package de.sgu.mocks.connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.junit.Test;

public class ConnectionPropertiesProviderTest {

    @Test
    public void testGetConnectionPropertiesSucceeds() throws URISyntaxException {
        ConnectionPropertiesProvider cpp = new ConnectionPropertiesFromFileProvider(new File(new URI("file:///C:/Users/stefan.wiese/git/sgu.mocks/src/de/sgu/mocks/connection/TestConnection.properties")));

        try {
            Properties props = cpp.getConnectionProps("aeon");
            assertEquals("testuser", props.getProperty("user"));
            assertEquals("testpassword", props.getProperty("password"));
        } catch (IOException e) {
            fail("Unexpected exception occured! " + e.getMessage());
        }
    }

}
