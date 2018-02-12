package by.scherbakov.audioportal.manager;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class {@code ConfigurationManager} is used to get messages according locale
 *
 * @author ScherbakovIlia
 */
public class MessageManager {
    private static final String RUSSIAN_LOCALE = "ru_RU";
    private static final String BELARUSIAN_LOCALE = "be_BE";
    private static final String FILE_PATH = "properties.content";

    private MessageManager() {
    }

    public static String getMessage(String key, String language) {
        ResourceBundle resourceBundle = null;
        Locale locale = null;
        if (language != null) {
            switch (language) {
                case RUSSIAN_LOCALE:
                    locale = new Locale("ru", "RU");
                    resourceBundle = ResourceBundle.getBundle(FILE_PATH, locale);
                    break;
                case BELARUSIAN_LOCALE:
                    locale = new Locale("be", "BE");
                    resourceBundle = ResourceBundle.getBundle(FILE_PATH, locale);
                    break;
                default:
                    resourceBundle = ResourceBundle.getBundle(FILE_PATH);
            }
        } else {
            resourceBundle = ResourceBundle.getBundle(FILE_PATH);
        }
        return resourceBundle.getString(key);
    }
}
