package by.scherbakov.audioportal.command;

import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

public class LanguageCommand extends ActionCommand {
    private static final String LANGUAGE_PARAMETER = "lang";
    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String LOGIN_PAGE = "path.page.login";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String newLocale = requestContent.getReguestParameterValue(LANGUAGE_PARAMETER);
        if (newLocale != null) {
            requestContent.setSessionAttributeValue(LOCALE_ATTRIBUTE, newLocale);
        }
        page = ConfigurationManager.getProperty(LOGIN_PAGE);
        return page;
    }
}
