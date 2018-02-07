package by.scherbakov.audioportal.command;

import by.scherbakov.audioportal.entity.Album;
import by.scherbakov.audioportal.entity.AudioTrack;
import by.scherbakov.audioportal.entity.Comment;
import by.scherbakov.audioportal.entity.Genre;
import by.scherbakov.audioportal.logic.AudioTrackLogic;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

import java.util.List;

public class TrackInfoCommand implements ActionCommand {
    private static final String SIGN_IN_ATTRIBUTE = "isSignIn";
    private static final String SIGN_IN_VALUE = "true";
    private static final String LOGIN_PAGE = "path.page.login";
    private static final String TRACK_ATTRIBUTE = "track";
    private static final String COMMENTS_ATTRIBUTE = "comments";
    private static final String ALBUM_ATTRIBUTE = "album";
    private static final String GENRE_ATTRIBUTE = "genre";
    private static final String TRACK_PAGE = "path.page.track";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page=null;
        String isSignIn = (String) requestContent.getSessionAttributeValue(SIGN_IN_ATTRIBUTE);
        if(SIGN_IN_VALUE.equals(isSignIn)) {
            String idTrack = requestContent.getReguestParameterValue(TRACK_ATTRIBUTE);
            AudioTrackLogic trackLogic = new AudioTrackLogic();
            List<Comment> comments = trackLogic.takeAllCommentsById(Integer.parseInt(idTrack));
            AudioTrack track = trackLogic.takeTrackById(idTrack);
            Album album = trackLogic.takeAlbumById(Integer.toString(track.getIdAlbum()));
            Genre genre = trackLogic.takeGenreById(Integer.toString(track.getIdGenre()));
            requestContent.setRequestAttributeValue(GENRE_ATTRIBUTE, genre);
            requestContent.setRequestAttributeValue(ALBUM_ATTRIBUTE, album);
            requestContent.setSessionAttributeValue(TRACK_ATTRIBUTE, track);
            requestContent.setRequestAttributeValue(COMMENTS_ATTRIBUTE, comments);
            page = ConfigurationManager.getProperty(TRACK_PAGE);
        }else {
            page = ConfigurationManager.getProperty(LOGIN_PAGE);
        }
        return page;
    }
}
