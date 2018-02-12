package by.scherbakov.audioportal.command;

import by.scherbakov.audioportal.entity.AudioTrack;
import by.scherbakov.audioportal.logic.AudioTrackLogic;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

import java.util.List;

/**
 * Class {@code MainCommand} is used to redirect to main page
 *
 * @author ScherbakovIlia
 * @see ActionCommand
 */

public class MainCommand implements ActionCommand {
    private static final String SIGN_IN_ATTRIBUTE = "isSignIn";
    private static final String SIGN_IN_VALUE = "true";
    private static final String LOGIN_PAGE = "path.page.login";
    private static final String TRACKS_ATTRIBUTE = "tracks";
    private static final String MAIN_PAGE = "path.page.main";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String isSignIn = (String) requestContent.getSessionAttributeValue(SIGN_IN_ATTRIBUTE);
        if (SIGN_IN_VALUE.equals(isSignIn)) {
            List<AudioTrack> tracks;
            AudioTrackLogic audioTrackLogic = new AudioTrackLogic();
            tracks = audioTrackLogic.takeAllTrack();
            requestContent.setRequestAttributeValue(TRACKS_ATTRIBUTE, tracks);
            page = ConfigurationManager.getProperty(MAIN_PAGE);
        } else {
            page = ConfigurationManager.getProperty(LOGIN_PAGE);
        }
        return page;
    }
}
