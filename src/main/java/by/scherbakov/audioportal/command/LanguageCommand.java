package by.scherbakov.audioportal.command;

import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

public class LanguageCommand implements ActionCommand {
    private static final String LANGUAGE_PARAMETER = "lang";
    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String SIGN_IN_ATTRIBUTE = "isSignIn";
    private static final String SIGN_IN_VALUE = "true";
    private static final String LOGIN_PAGE = "path.page.login";
    private static final String MAIN_PAGE = "/web?command=main";


    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String newLocale = requestContent.getReguestParameterValue(LANGUAGE_PARAMETER);
        if (newLocale != null) {
            requestContent.setSessionAttributeValue(LOCALE_ATTRIBUTE, newLocale);
        }
        String isSignIn = (String) requestContent.getSessionAttributeValue(SIGN_IN_ATTRIBUTE);
        if(SIGN_IN_VALUE.equalsIgnoreCase(isSignIn)){
            page = MAIN_PAGE;
        }else {
            page = ConfigurationManager.getProperty(LOGIN_PAGE);
        }
        return page;
    }
}
