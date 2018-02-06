package by.scherbakov.audioportal.command.admin;

import by.scherbakov.audioportal.command.ActionCommand;
import by.scherbakov.audioportal.entity.User;
import by.scherbakov.audioportal.logic.AudioTrackLogic;
import by.scherbakov.audioportal.logic.UserLogic;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

import java.util.List;

public class AllClientsCommand implements ActionCommand {
    private static final String CLIENTS_ATTRIBUTE = "clients";
    private static final String ALL_USERS_PAGE = "path.page.allUsers";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        List<User> clients;
        UserLogic userLogic = new UserLogic();
        clients = userLogic.takeAllClient();
        requestContent.setRequestAttributeValue(CLIENTS_ATTRIBUTE, clients);
        page = ConfigurationManager.getProperty(ALL_USERS_PAGE);
        return page;
    }
}
