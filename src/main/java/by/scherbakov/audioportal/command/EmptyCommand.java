package by.scherbakov.audioportal.command;

import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

/**
 * Class {@code EmptyCommand} is used to redirect to page
 * when command doesn't exists
 *
 * @author ScherbakovIlia
 * @see ActionCommand
 */

public class EmptyCommand implements ActionCommand {
    private static final String ERROR_PAGE = "path.page.error";

    @Override
    public String execute(SessionRequestContent requestContent) {
        return ConfigurationManager.getProperty(ERROR_PAGE);
    }
}
