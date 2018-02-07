package by.scherbakov.audioportal.command.admin;

import by.scherbakov.audioportal.command.ActionCommand;
import by.scherbakov.audioportal.entity.User;
import by.scherbakov.audioportal.logic.AudioTrackLogic;
import by.scherbakov.audioportal.logic.UserLogic;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

import java.util.List;

public class AllClientsCommand implements ActionCommand {
    private static final String SIGN_IN_ATTRIBUTE = "isSignIn";
    private static final String USER_ATTRIBUTE = "user";
    private static final String SIGN_IN_VALUE = "true";
    private static final String ADMIN_ROLE = "admin";
    private static final String LOGIN_PAGE = "path.page.login";
    private static final String MAIN_PAGE_ACTION = "/web?command=main";
    private static final String CLIENTS_ATTRIBUTE = "clients";
    private static final String ALL_USERS_PAGE = "path.page.allUsers";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String isSignIn = (String) requestContent.getSessionAttributeValue(SIGN_IN_ATTRIBUTE);
        User user = (User) requestContent.getSessionAttributeValue(USER_ATTRIBUTE);
        if (SIGN_IN_VALUE.equals(isSignIn) && !ADMIN_ROLE.equals(user.getRole())) {
            page = MAIN_PAGE_ACTION;
        } else if (ADMIN_ROLE.equals(user.getRole())) {
            List<User> clients;
            UserLogic userLogic = new UserLogic();
            clients = userLogic.takeAllClient();
            requestContent.setRequestAttributeValue(CLIENTS_ATTRIBUTE, clients);
            page = ConfigurationManager.getProperty(ALL_USERS_PAGE);
        }else {
            page = ConfigurationManager.getProperty(LOGIN_PAGE);
        }
        return page;
    }
}
