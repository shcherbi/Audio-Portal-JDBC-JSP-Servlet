package by.scherbakov.audioportal.command;

import by.scherbakov.audioportal.logic.LoginLogic;

public enum CommandType {
    LOGIN(new LoginCommand(new LoginLogic())),
    LANGUAGE(new LanguageCommand()),
    REGISTRATION(new RegistrationCommand()),
    MAIN(new MainCommand());

    ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommandType() {
        return command;
    }
}
