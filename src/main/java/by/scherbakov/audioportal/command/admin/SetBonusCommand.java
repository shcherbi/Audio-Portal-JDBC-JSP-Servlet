package by.scherbakov.audioportal.command.admin;

import by.scherbakov.audioportal.command.ActionCommand;
import by.scherbakov.audioportal.entity.User;
import by.scherbakov.audioportal.logic.UserLogic;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.manager.MessageManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

public class SetBonusCommand implements ActionCommand {
    private static final String SIGN_IN_ATTRIBUTE = "isSignIn";
    private static final String USER_ATTRIBUTE = "user";
    private static final String SIGN_IN_VALUE = "true";
    private static final String ADMIN_ROLE = "admin";
    private static final String LOGIN_PAGE = "path.page.login";
    private static final String MAIN_PAGE_ACTION = "/web?command=main";
    private static final String LOGIN_ATTRIBUTE = "login";
    private static final String BONUS_ATTRIBUTE = "bonus";
    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String MISTAKE_ATTRIBUTE = "adminFunctionalError";
    private static final String ALL_CLIENTS_PAGE_ACTION = "/web?command=all_clients";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String isSignIn = (String) requestContent.getSessionAttributeValue(SIGN_IN_ATTRIBUTE);
        User user = (User) requestContent.getSessionAttributeValue(USER_ATTRIBUTE);
        if (SIGN_IN_VALUE.equals(isSignIn) && !ADMIN_ROLE.equals(user.getRole())) {
            page = MAIN_PAGE_ACTION;
        } else if (ADMIN_ROLE.equals(user.getRole())) {
            String login = requestContent.getReguestParameterValue(LOGIN_ATTRIBUTE);
            String bonus = requestContent.getReguestParameterValue(BONUS_ATTRIBUTE);
            UserLogic userLogic = new UserLogic();
            String message = userLogic.setBonus(login, bonus);
            if (!message.isEmpty()) {
                String errorMessage = MessageManager.getMessage(message,
                        (String) requestContent.getSessionAttributeValue(LOCALE_ATTRIBUTE));
                requestContent.setRequestAttributeValue(MISTAKE_ATTRIBUTE, errorMessage);
            }
            page = ALL_CLIENTS_PAGE_ACTION;
        }else {
            page = ConfigurationManager.getProperty(LOGIN_PAGE);
        }
        return page;
    }
}
