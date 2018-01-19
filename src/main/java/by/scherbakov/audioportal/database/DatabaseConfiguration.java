package by.scherbakov.audioportal.database;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class DatabaseConfiguration {
    private final String URL;
    private final String USER;
    private final String PASSWORD;
    private final int POOL_SIZE;

    public DatabaseConfiguration() {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("properties.dbConfig");
            URL = resourceBundle.getString("db.url");
            USER = resourceBundle.getString("db.user");
            PASSWORD = resourceBundle.getString("db.password");
            POOL_SIZE = Integer.parseInt(resourceBundle.getString("db.poolSize"));
        } catch (NumberFormatException | MissingResourceException e) {
            throw new RuntimeException();
        }
    }

    public String getURL() {
        return URL;
    }

    public String getUser() {
        return USER;
    }

    public String getPassword() {
        return PASSWORD;
    }

    public int getPoolSize() {
        return POOL_SIZE;
    }
}
