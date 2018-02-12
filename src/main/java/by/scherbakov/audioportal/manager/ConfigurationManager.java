package by.scherbakov.audioportal.manager;

import java.util.ResourceBundle;

/**
 * Class {@code ConfigurationManager} is used to get path to pages
 *
 * @author ScherbakovIlia
 */
public class ConfigurationManager {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("properties.config");

    private ConfigurationManager(){}

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
