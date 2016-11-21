package de.sgu.mocks.connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class ConnectionPropertiesFromFileProvider implements ConnectionPropertiesProvider {

    private File file;

    public ConnectionPropertiesFromFileProvider(File file) {
        this.file = file;
    }

    @Override
    public Properties getConnectionProps(String mall) throws IOException {
        InputStream input = new FileInputStream(file);
        Properties inputProps = new Properties();
        inputProps.load(input);
        
        Properties resultProps = new Properties();
        Set<Object> keySet = inputProps.keySet();
        for (Object key : keySet) {
            String propKey = (String) key;
            if (propKey.startsWith(mall + ".")){
                resultProps.put(propKey.substring((mall + ".").length()), inputProps.getProperty(propKey));
            }
        }
        return resultProps;
    }

}
