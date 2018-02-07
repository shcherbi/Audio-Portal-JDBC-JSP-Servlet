package by.scherbakov.audioportal.command.client;

import by.scherbakov.audioportal.command.ActionCommand;
import by.scherbakov.audioportal.entity.AudioTrack;
import by.scherbakov.audioportal.entity.User;
import by.scherbakov.audioportal.logic.AudioTrackLogic;
import by.scherbakov.audioportal.logic.OrderLogic;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.manager.MessageManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

import java.util.List;

public class OrderedTrackCommand implements ActionCommand {
    private static final String SIGN_IN_ATTRIBUTE = "isSignIn";
    private static final String SIGN_IN_VALUE = "true";
    private static final String LOGIN_PAGE = "path.page.login";
    private static final String USER_ATTRIBUTE = "user";
    private static final String ORDERED_TRACK_ATTRIBUTE = "orderedTrack";
    private static final String ORDERED_TRACK_PAGE = "path.page.orderedTracks";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String isSignIn = (String) requestContent.getSessionAttributeValue(SIGN_IN_ATTRIBUTE);
        if (SIGN_IN_VALUE.equals(isSignIn)) {
            User user = (User) requestContent.getSessionAttributeValue(USER_ATTRIBUTE);
            String login = user.getLogin();
            List<AudioTrack> tracks;
            AudioTrackLogic audioTrackLogic = new AudioTrackLogic();
            tracks = audioTrackLogic.takeOrderedTracks(login);
            requestContent.setRequestAttributeValue(ORDERED_TRACK_ATTRIBUTE, tracks);
            page = ConfigurationManager.getProperty(ORDERED_TRACK_PAGE);
        } else {
            page = ConfigurationManager.getProperty(LOGIN_PAGE);
        }
        return page;
    }
}
