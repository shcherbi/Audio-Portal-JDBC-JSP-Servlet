package by.scherbakov.audioportal.command;

import by.scherbakov.audioportal.command.account.ChangeEmailCommand;
import by.scherbakov.audioportal.command.account.ChangeLoginCommand;
import by.scherbakov.audioportal.command.account.ChangePasswordCommand;
import by.scherbakov.audioportal.command.admin.*;
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
    ADD_AUDIO_TRACK(new AddTrackCommand()),
    ADD_ALBUM(new AddAlbumCommand()),
    ADD_GENRE(new AddGenreCommand()),
    CHANGE_LOGIN(new ChangeLoginCommand()),
    CHANGE_EMAIL(new ChangeEmailCommand()),
    CHANGE_PASSWORD(new ChangePasswordCommand()),
    CHANGE_ARTIST(new ChangeArtistCommand()),
    CHANGE_TRACK_NAME(new ChangeTrackNameCommand()),
    CHANGE_ALBUM(new ChangeAlbumCommand()),
    CHANGE_GENRE(new ChangeGenreCommand()),
    CHANGE_LINK(new ChangeLinkCommand()),
    CHANGE_IMAGE_LINK(new ChangeImageLinkCommand()),
    CHANGE_PRICE(new ChangePriceCommand()),
    ALL_CLIENTS(new AllClientsCommand()),
    GRANT_USER(new GrantUserCommand()),
    SET_BONUS(new SetBonusCommand()),
    DELETE_USER(new DeleteUserCommand()),
    DELETE_TRACK(new DeleteTrackCommand());

    ActionCommand command;

    CommandType(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCommandType() {
        return command;
    }
}
