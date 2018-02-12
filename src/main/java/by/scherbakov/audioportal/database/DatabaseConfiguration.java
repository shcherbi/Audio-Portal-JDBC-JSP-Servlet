package by.scherbakov.audioportal.database;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * {@code DatabaseConfiguration} related with initializing params for database
 *
 * @author ScherbakovIlia
 */

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

    /**
     * Retrieved URL to initialize database
     *
     * @return url
     */
    public String getURL() {
        return URL;
    }

    /**
     * Retrieved user to initialize database
     *
     * @return user
     */
    public String getUser() {
        return USER;
    }

    /**
     * Retrieved password to initialize database
     *
     * @return password
     */
    public String getPassword() {
        return PASSWORD;
    }

    /**
     * Retrieved pool size to initialize database
     *
     * @return pool size
     */
    public int getPoolSize() {
        return POOL_SIZE;
    }
}
