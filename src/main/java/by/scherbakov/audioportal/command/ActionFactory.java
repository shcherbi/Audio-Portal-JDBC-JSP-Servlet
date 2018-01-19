package by.scherbakov.audioportal.command;

import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.manager.MessageManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActionFactory {
    public static final Logger LOGGER = LogManager.getLogger(ActionFactory.class);
    private static final String COMMAND_PARAMETER = "command";
    private static final String WRONG_ACTION_ATTRIBUTE = "wrongAction";
    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String WRONG_ACTION_MESSAGE = "message.login.wrongAction";

    public static ActionCommand defineCommand(SessionRequestContent requestContent) {
        ActionCommand current = new EmptyCommand();
        String action = requestContent.getReguestParameterValue(COMMAND_PARAMETER);
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandType currentType= CommandType.valueOf(action.toUpperCase());
            current = currentType.getCommandType();
        } catch (IllegalArgumentException e) {
            LOGGER.error("Command not defined",e);
            String pageMessage = MessageManager.getMessage(WRONG_ACTION_MESSAGE,
                    (String) requestContent.getSessionAttributeValue(LOCALE_ATTRIBUTE));
            requestContent.setRequestAttributeValue(WRONG_ACTION_ATTRIBUTE, pageMessage);
        }
        return current;
    }
}
