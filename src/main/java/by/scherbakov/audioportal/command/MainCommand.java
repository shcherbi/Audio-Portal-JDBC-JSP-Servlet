package by.scherbakov.audioportal.command;

import by.scherbakov.audioportal.entity.AudioTrack;
import by.scherbakov.audioportal.logic.AudioTrackLogic;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

import java.util.List;

public class MainCommand extends ActionCommand {
    public static final String TRACKS_ATTRIBUTE = "tracks";
    private static final String MAIN_PAGE = "path.page.main";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        List<AudioTrack> tracks;
        AudioTrackLogic audioTrackLogic = new AudioTrackLogic();
        tracks = audioTrackLogic.takeAllTrack();
        requestContent.setSessionAttributeValue(TRACKS_ATTRIBUTE, tracks);
        page = ConfigurationManager.getProperty(MAIN_PAGE);
        return page;
    }
}
