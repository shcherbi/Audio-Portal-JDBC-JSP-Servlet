package by.scherbakov.audioportal.command;

import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

public class EmptyCommand implements ActionCommand {
    public static final String ERROR_PAGE = "path.page.error";
    @Override
    public String execute(SessionRequestContent requestContent) {
        return ConfigurationManager.getProperty(ERROR_PAGE);
    }
}
