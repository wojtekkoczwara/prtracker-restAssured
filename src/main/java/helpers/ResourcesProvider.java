package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ResourcesProvider {

    public static final String FILEPATH = "src/test/java/testResources/testResources.properties";

    public static String read(String propertyPath, String propertyName) {
        var properties = new Properties();
        try {
            properties.load(new FileInputStream(propertyPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(propertyName).trim();
    }

    public static String getBaseUri(){
        String reader = read(FILEPATH, "baseURI");
        return read(FILEPATH, "baseURI");
    }

}
