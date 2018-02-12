package by.scherbakov.audioportal.command;

import by.scherbakov.audioportal.logic.UserLogic;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.manager.MessageManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

/**
 * Class {@code RegistrationCommand} is used to register user
 *
 * @author ScherbakovIlia
 * @see ActionCommand
 */

public class RegistrationCommand implements ActionCommand {
    private static final String NICKNAME_PARAMETER = "nickname";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String EMAIL_PARAMETER = "email";
    private static final String ROLE_PARAMETER = "role";
    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String MISTAKE_ATTRIBUTE = "userExist";
    private static final String LOGIN_PAGE = "path.page.login";
    private static final String REGISTER_PAGE = "path.page.register";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        UserLogic userLogic = new UserLogic();
        String nickname = requestContent.getRequestParameterValue(NICKNAME_PARAMETER);
        String password = requestContent.getRequestParameterValue(PASSWORD_PARAMETER);
        String email = requestContent.getRequestParameterValue(EMAIL_PARAMETER);
        String role = requestContent.getRequestParameterValue(ROLE_PARAMETER);
        String message = userLogic.registerUser(nickname, password, email, role);
        if (!message.isEmpty()) {
            String errorMessage = MessageManager.getMessage(message,
                    (String) requestContent.getSessionAttributeValue(LOCALE_ATTRIBUTE));
            requestContent.setRequestAttributeValue(MISTAKE_ATTRIBUTE, errorMessage);
            page = ConfigurationManager.getProperty(REGISTER_PAGE);
        }else {
            page = ConfigurationManager.getProperty(LOGIN_PAGE);
        }
        return page;
    }
}
