package by.scherbakov.audioportal.command;

import by.scherbakov.audioportal.logic.UserLogic;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

public class RegistrationCommand extends ActionCommand {
    private static final String NICKNAME_PARAMETER = "nickname";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String EMAIL_PARAMETER = "email";
    private static final String ROLE_PARAMETER = "role";
    private static final String LOGIN_PAGE = "path.page.login";

    @Override
    public String execute(SessionRequestContent requestContent) {
        UserLogic userLogic = new UserLogic();
        String nickname = requestContent.getReguestParameterValue(NICKNAME_PARAMETER);
        String password = requestContent.getReguestParameterValue(PASSWORD_PARAMETER);
        String email = requestContent.getReguestParameterValue(EMAIL_PARAMETER);
        String role = requestContent.getReguestParameterValue(ROLE_PARAMETER);
        userLogic.registerUser(nickname, password, email, role);
        return ConfigurationManager.getProperty(LOGIN_PAGE);
    }
}
