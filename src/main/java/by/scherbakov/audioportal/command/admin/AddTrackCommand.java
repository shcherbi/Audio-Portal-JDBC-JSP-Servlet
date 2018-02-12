package by.scherbakov.audioportal.command.admin;

import by.scherbakov.audioportal.command.ActionCommand;
import by.scherbakov.audioportal.entity.User;
import by.scherbakov.audioportal.logic.AudioTrackLogic;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.manager.MessageManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

/**
 * Class {@code AddTrackCommand} is used to add audio track
 *
 * @author ScherbakovIlia
 * @see ActionCommand
 */

public class AddTrackCommand implements ActionCommand {
    private static final String USER_ATTRIBUTE = "user";
    private static final String ADMIN_ROLE = "admin";
    private static final String LOGIN_PAGE = "path.page.login";
    private static final String NAME_PARAMETER = "name";
    private static final String ARTIST_PARAMETER = "artist";
    private static final String ALBUM_PARAMETER = "album";
    private static final String STUDIO_PARAMETER = "studio";
    private static final String DATE_PARAMETER = "date";
    private static final String GENRE_PARAMETER = "genre";
    private static final String PRICE_PARAMETER = "price";
    private static final String LINK_PARAMETER = "link";
    private static final String IMAGE_LINK_PARAMETER = "imageLink";
    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String MISTAKE_ATTRIBUTE = "mistakeSongName";
    private static final String MAIN_PAGE_ACTION = "/web?command=main";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        User user = (User) requestContent.getSessionAttributeValue(USER_ATTRIBUTE);
        if (user == null) {
            page = ConfigurationManager.getProperty(LOGIN_PAGE);
        } else if (ADMIN_ROLE.equals(user.getRole())) {
            String name = requestContent.getRequestParameterValue(NAME_PARAMETER);
            String artist = requestContent.getRequestParameterValue(ARTIST_PARAMETER);
            String album = requestContent.getRequestParameterValue(ALBUM_PARAMETER);
            String studio = requestContent.getRequestParameterValue(STUDIO_PARAMETER);
            String date = requestContent.getRequestParameterValue(DATE_PARAMETER);
            String genre = requestContent.getRequestParameterValue(GENRE_PARAMETER);
            String price = requestContent.getRequestParameterValue(PRICE_PARAMETER);
            String link = requestContent.getRequestParameterValue(LINK_PARAMETER);
            String imageLink = requestContent.getRequestParameterValue(IMAGE_LINK_PARAMETER);
            AudioTrackLogic audioTrackLogic = new AudioTrackLogic();
            String message = audioTrackLogic.addTrack(name, artist, album, studio, date, genre, price, link, imageLink);
            if (!message.isEmpty()) {
                String errorMessage = MessageManager.getMessage(message,
                        (String) requestContent.getSessionAttributeValue(LOCALE_ATTRIBUTE));
                requestContent.setRequestAttributeValue(MISTAKE_ATTRIBUTE, errorMessage);
            }
            page = MAIN_PAGE_ACTION;
        } else {
            page = MAIN_PAGE_ACTION;
        }
        return page;
    }
}