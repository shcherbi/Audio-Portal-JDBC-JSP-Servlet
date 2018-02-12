package by.scherbakov.audioportal.command.admin;

import by.scherbakov.audioportal.command.ActionCommand;
import by.scherbakov.audioportal.entity.User;
import by.scherbakov.audioportal.logic.UserLogic;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

import java.util.List;

/**
 * Class {@code AllClientsCommand} is used to view all clients
 *
 * @author ScherbakovIlia
 * @see ActionCommand
 */

public class AllClientsCommand implements ActionCommand {
    private static final String USER_ATTRIBUTE = "user";
    private static final String ADMIN_ROLE = "admin";
    private static final String LOGIN_PAGE = "path.page.login";
    private static final String MAIN_PAGE_ACTION = "/web?command=main";
    private static final String CLIENTS_ATTRIBUTE = "clients";
    private static final String ALL_USERS_PAGE = "path.page.allUsers";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        User user = (User) requestContent.getSessionAttributeValue(USER_ATTRIBUTE);
        if (user==null) {
            page = ConfigurationManager.getProperty(LOGIN_PAGE);
        } else if (ADMIN_ROLE.equals(user.getRole())) {
            List<User> clients;
            UserLogic userLogic = new UserLogic();
            clients = userLogic.takeAllClient();
            requestContent.setRequestAttributeValue(CLIENTS_ATTRIBUTE, clients);
            page = ConfigurationManager.getProperty(ALL_USERS_PAGE);
        }else {
            page = MAIN_PAGE_ACTION;
        }
        return page;
    }
}
