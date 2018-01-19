package by.scherbakov.audioportal.manager;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("properties.config");

    private ConfigurationManager(){}

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
