package by.scherbakov.audioportal.command;

import by.scherbakov.audioportal.servlet.SessionRequestContent;

/**
 * Interface {@code ActionCommand} is used for all commands.
 * Contain common abstract method execute.
 *
 * @author ScherbakovIlia
 */

public interface ActionCommand{
    /**
     * Does all necessary work to handle command
     *
     * @param requestContent is content of request
     * @return path to page as a result of command
     */
    String execute(SessionRequestContent requestContent);
}
