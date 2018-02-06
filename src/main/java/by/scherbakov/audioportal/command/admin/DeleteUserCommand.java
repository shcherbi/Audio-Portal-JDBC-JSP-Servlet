package by.scherbakov.audioportal.command.admin;

import by.scherbakov.audioportal.command.ActionCommand;
import by.scherbakov.audioportal.logic.UserLogic;
import by.scherbakov.audioportal.manager.MessageManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

public class DeleteUserCommand implements ActionCommand {
    private static final String LOGIN_ATTRIBUTE = "login";
    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String MISTAKE_ATTRIBUTE = "adminFunctionalError";
    private static final String ALL_CLIENTS_PAGE_ACTION = "/web?command=all_clients";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String login = requestContent.getReguestParameterValue(LOGIN_ATTRIBUTE);
        UserLogic userLogic = new UserLogic();
        String message = userLogic.deleteUser(login);
        if (!message.isEmpty()) {
            String errorMessage = MessageManager.getMessage(message,
                    (String) requestContent.getSessionAttributeValue(LOCALE_ATTRIBUTE));
            requestContent.setRequestAttributeValue(MISTAKE_ATTRIBUTE, errorMessage);
        }
        page = ALL_CLIENTS_PAGE_ACTION;
        return page;
    }
}
