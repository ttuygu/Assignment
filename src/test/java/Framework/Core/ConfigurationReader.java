package Framework.Core;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties properties;

    static {
        try {
            // LOAD GENERAL PROPERTIES
            String path = "configuration.properties";
            FileInputStream input = new FileInputStream(path);      // FIS provides access to file

            properties = new Properties();                          // initialize configFile
            properties.load(input);                                 //load properties file

            // LOAD ENVIRONMENT SPECIFIC PROPERTIES
            if (System.getProperty("env") != null) {
                path = "src/test/resources/environment/" + System.getProperty("environment") + ".properties";
            } else {
                path = "src/test/resources/environment/" + properties.getProperty("environment") + ".properties";
            }
            input = new FileInputStream(path);
            properties.load(input);
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}