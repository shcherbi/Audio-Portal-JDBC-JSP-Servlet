package by.scherbakov.audioportal.command;

import by.scherbakov.audioportal.entity.Album;
import by.scherbakov.audioportal.entity.AudioTrack;
import by.scherbakov.audioportal.entity.Comment;
import by.scherbakov.audioportal.entity.Genre;
import by.scherbakov.audioportal.logic.AlbumLogic;
import by.scherbakov.audioportal.logic.AudioTrackLogic;
import by.scherbakov.audioportal.logic.CommentLogic;
import by.scherbakov.audioportal.logic.GenreLogic;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

import java.util.List;

/**
 * Class {@code TrackInfoCommand} is used to view track info
 *
 * @author ScherbakovIlia
 * @see ActionCommand
 */

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
            String idTrack = requestContent.getRequestParameterValue(TRACK_ATTRIBUTE);
            AudioTrackLogic trackLogic = new AudioTrackLogic();
            AudioTrack track = trackLogic.takeTrackById(idTrack);
            AlbumLogic albumLogic = new AlbumLogic();
            Album album = albumLogic.takeAlbumById(Integer.toString(track.getIdAlbum()));
            GenreLogic genreLogic = new GenreLogic();
            Genre genre = genreLogic.takeGenreById(Integer.toString(track.getIdGenre()));
            CommentLogic commentLogic = new CommentLogic();
            List<Comment> comments = commentLogic.takeAllCommentsById(Integer.parseInt(idTrack));
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
