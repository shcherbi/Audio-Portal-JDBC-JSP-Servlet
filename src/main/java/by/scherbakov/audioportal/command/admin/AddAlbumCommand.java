package by.scherbakov.audioportal.command.admin;

import by.scherbakov.audioportal.command.ActionCommand;
import by.scherbakov.audioportal.entity.User;
import by.scherbakov.audioportal.logic.AlbumLogic;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.manager.MessageManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

/**
 * Class {@code AddAlbumCommand} is used to add
 * album
 *
 * @author ScherbakovIlia
 * @see ActionCommand
 */

public class AddAlbumCommand implements ActionCommand {
    private static final String USER_ATTRIBUTE = "user";
    private static final String ADMIN_ROLE = "admin";
    private static final String LOGIN_PAGE = "path.page.login";
    private static final String ALBUM_PARAMETER = "album";
    private static final String STUDIO_PARAMETER = "studio";
    private static final String DATE_PARAMETER = "date";
    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String MISTAKE_ATTRIBUTE = "mistakeSongName";
    private static final String MAIN_PAGE_ACTION = "/web?command=main";
    private static final String ERROR_ADD_MESSAGE = "message.main.albumAddError";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        User user = (User) requestContent.getSessionAttributeValue(USER_ATTRIBUTE);
        if (user == null) {
            page = ConfigurationManager.getProperty(LOGIN_PAGE);
        } else if (ADMIN_ROLE.equals(user.getRole())) {
            String album = requestContent.getRequestParameterValue(ALBUM_PARAMETER);
            String studio = requestContent.getRequestParameterValue(STUDIO_PARAMETER);
            String date = requestContent.getRequestParameterValue(DATE_PARAMETER);
            AlbumLogic albumLogic = new AlbumLogic();
            boolean isAdded = albumLogic.addAlbum(album, studio, date);
            if (!isAdded) {
                String errorMessage = MessageManager.getMessage(ERROR_ADD_MESSAGE,
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
