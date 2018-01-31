package by.scherbakov.audioportal.command;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    LANGUAGE(new LanguageCommand()),
    REGISTRATION(new RegistrationCommand()),
    MAIN(new MainCommand()),
    TRACK_INFO(new TrackInfoCommand()),
    COMMENT_ADD(new CommentAddCommand()),
    ORDER_LIST_ADD(new OrderListAddCommand()),
    ORDER_LIST_REMOVE(new OrderListRemoveCommand()),
    PRE_ORDER(new PreOrderCommand()),
    ORDER(new OrderCommand());

    ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommandType() {
        return command;
    }
}
