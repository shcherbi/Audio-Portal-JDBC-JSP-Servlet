package by.scherbakov.audioportal.command;

import by.scherbakov.audioportal.command.admin.AddTrackCommand;
import by.scherbakov.audioportal.command.client.*;

public enum CommandType {
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    LANGUAGE(new LanguageCommand()),
    REGISTRATION(new RegistrationCommand()),
    MAIN(new MainCommand()),
    FIND(new FindTrackCommand()),
    TRACK_INFO(new TrackInfoCommand()),
    COMMENT_ADD(new CommentAddCommand()),
    ORDER_LIST_ADD(new OrderListAddCommand()),
    ORDER_LIST_REMOVE(new OrderListRemoveCommand()),
    PRE_ORDER(new PreOrderCommand()),
    ORDER(new OrderCommand()),
    ORDERED_TRACKS(new OrderedTrackCommand()),
    ADD_AUDIO_TRACK(new AddTrackCommand());

    ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommandType() {
        return command;
    }
}
