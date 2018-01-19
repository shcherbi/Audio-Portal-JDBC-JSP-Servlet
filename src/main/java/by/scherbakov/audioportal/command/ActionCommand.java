package by.scherbakov.audioportal.command;

import by.scherbakov.audioportal.servlet.SessionRequestContent;

public abstract class ActionCommand{
    public abstract String execute(SessionRequestContent request);
}
