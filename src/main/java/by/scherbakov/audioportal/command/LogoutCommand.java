package by.scherbakov.audioportal.command;

import by.scherbakov.audioportal.command.ActionCommand;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

public class LogoutCommand implements ActionCommand {
    private static final String SIGN_IN_VALUE = "true";
    private static final String USER_ATTRIBUTE = "user";
    private static final String SIGN_IN_ATTRIBUTE = "isSignIn";
    private static final String TRACKS_ATTRIBUTE = "tracks";
    private static final String TRACK_ATTRIBUTE = "track";
    private static final String ORDER_LIST_ATTRIBUTE = "orderList";
    private static final String LOGIN_PAGE = "path.page.login";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String isSignIn = (String) requestContent.getSessionAttributeValue(SIGN_IN_ATTRIBUTE);
        if(SIGN_IN_VALUE.equals(isSignIn)) {
            requestContent.setSessionAttributeValue(USER_ATTRIBUTE, null);
            requestContent.setSessionAttributeValue(SIGN_IN_ATTRIBUTE, null);
            requestContent.setSessionAttributeValue(TRACKS_ATTRIBUTE, null);
            requestContent.setSessionAttributeValue(TRACK_ATTRIBUTE, null);
            requestContent.setSessionAttributeValue(ORDER_LIST_ATTRIBUTE, null);
            page = ConfigurationManager.getProperty(LOGIN_PAGE);
        }else {
            page = ConfigurationManager.getProperty(LOGIN_PAGE);
        }
        return page;
    }
}
