package by.scherbakov.audioportal.command;

import by.scherbakov.audioportal.entity.User;
import by.scherbakov.audioportal.logic.LoginLogic;
import by.scherbakov.audioportal.logic.UserLogic;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.manager.MessageManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

/**
 * Class {@code LoginCommand} is used to sign in user
 *
 * @author ScherbakovIlia
 * @see ActionCommand
 */

public class LoginCommand implements ActionCommand {
    private static final String NICKNAME_PARAMETER = "nickname";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String SIGN_IN_ATTRIBUTE = "isSignIn";
    private static final String SIGN_IN_VALUE = "true";
    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String USER_ATTRIBUTE = "user";
    private static final String LOGIN_ERROR_ATTRIBUTE = "errorLoginPassMessage";
    private static final String MAIN_PAGE_ACTION = "/web?command=main";
    private static final String LOGIN_PAGE = "path.page.login";
    private static final String LOGIN_PASSWORD_MESSAGE = "message.login.errorLoginPass";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String nickname = requestContent.getRequestParameterValue(NICKNAME_PARAMETER);
        String password = requestContent.getRequestParameterValue(PASSWORD_PARAMETER);
        LoginLogic loginLogic = new LoginLogic();
        if (loginLogic.checkLogin(nickname, password)) {
            UserLogic userLogic = new UserLogic();
            User user = userLogic.findUser(nickname);
            requestContent.setSessionAttributeValue(SIGN_IN_ATTRIBUTE, SIGN_IN_VALUE);
            requestContent.setSessionAttributeValue(USER_ATTRIBUTE, user);
            page = MAIN_PAGE_ACTION;
        } else {
            String pageMessage = MessageManager.getMessage(LOGIN_PASSWORD_MESSAGE,
                    (String) requestContent.getSessionAttributeValue(LOCALE_ATTRIBUTE));
            requestContent.setRequestAttributeValue(LOGIN_ERROR_ATTRIBUTE, pageMessage);
            page = ConfigurationManager.getProperty(LOGIN_PAGE);
        }
        return page;
    }
}
