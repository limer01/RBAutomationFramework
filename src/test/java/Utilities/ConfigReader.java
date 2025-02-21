package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties properties;
    private final String configFilePath = "src/test/resources/config.properties";

    public ConfigReader() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            properties.load(fis);  // Load properties file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to get a property value by key
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
