package by.scherbakov.audioportal.command;

import by.scherbakov.audioportal.servlet.SessionRequestContent;

public interface ActionCommand{
    String execute(SessionRequestContent request);
}
