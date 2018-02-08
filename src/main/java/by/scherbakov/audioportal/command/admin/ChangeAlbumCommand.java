package by.scherbakov.audioportal.command.admin;

import by.scherbakov.audioportal.command.ActionCommand;
import by.scherbakov.audioportal.dao.AlbumDAO;
import by.scherbakov.audioportal.entity.Album;
import by.scherbakov.audioportal.entity.AudioTrack;
import by.scherbakov.audioportal.entity.User;
import by.scherbakov.audioportal.logic.AlbumLogic;
import by.scherbakov.audioportal.logic.AudioTrackLogic;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.manager.MessageManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

public class ChangeAlbumCommand implements ActionCommand {
    private static final String USER_ATTRIBUTE = "user";
    private static final String ADMIN_ROLE = "admin";
    private static final String LOGIN_PAGE = "path.page.login";
    private static final String MAIN_PAGE_ACTION = "/web?command=main";
    private static final String TRACK_ATTRIBUTE = "track";
    private static final String ALBUM_PARAMETER = "album";
    private static final String STUDIO_PARAMETER = "studio";
    private static final String DATE_PARAMETER = "date";
    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String EMPTY_FIELD = "";
    private static final String MISTAKE_ATTRIBUTE = "updateAudioTrackError";
    private static final String TRACK_PAGE_ACTION = "/web?command=track_info&track=";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        User user = (User) requestContent.getSessionAttributeValue(USER_ATTRIBUTE);
        if (user==null) {
            page = ConfigurationManager.getProperty(LOGIN_PAGE);
        } else if (ADMIN_ROLE.equals(user.getRole())) {
            AudioTrack track = (AudioTrack) requestContent.getSessionAttributeValue(TRACK_ATTRIBUTE);
            String albumName = requestContent.getReguestParameterValue(ALBUM_PARAMETER);
            String studio = requestContent.getReguestParameterValue(STUDIO_PARAMETER);
            String date = requestContent.getReguestParameterValue(DATE_PARAMETER);
            AlbumLogic albumLogic = new AlbumLogic();
            Album album = albumLogic.updateAlbum(albumName, studio, date);
            String message = null;
            if(album!=null){
                track.setIdAlbum(album.getId());
                AudioTrackLogic audioTrackLogic = new AudioTrackLogic();
                message = audioTrackLogic.updateAudioTrack(track);
            }else {
                message=EMPTY_FIELD;
            }
            if (!message.isEmpty()) {
                String errorMessage = MessageManager.getMessage(message,
                        (String) requestContent.getSessionAttributeValue(LOCALE_ATTRIBUTE));
                requestContent.setRequestAttributeValue(MISTAKE_ATTRIBUTE, errorMessage);
            }
            page = TRACK_PAGE_ACTION + track.getId();
        }else {
            page = MAIN_PAGE_ACTION;
        }
        return page;
    }
}
