package by.scherbakov.audioportal.command.admin;

import by.scherbakov.audioportal.command.ActionCommand;
import by.scherbakov.audioportal.entity.User;
import by.scherbakov.audioportal.logic.GenreLogic;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.manager.MessageManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

public class AddGenreCommand implements ActionCommand {
    private static final String USER_ATTRIBUTE = "user";
    private static final String ADMIN_ROLE = "admin";
    private static final String LOGIN_PAGE = "path.page.login";
    private static final String GENRE_PARAMETER = "genre";
    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String MISTAKE_ATTRIBUTE = "mistakeSongName";
    private static final String MAIN_PAGE_ACTION = "/web?command=main";
    private static final String ERROR_ADD_MESSAGE = "message.main.genreAddError";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        User user = (User) requestContent.getSessionAttributeValue(USER_ATTRIBUTE);
        if (user==null) {
            page = ConfigurationManager.getProperty(LOGIN_PAGE);
        } else if (ADMIN_ROLE.equals(user.getRole())) {
            String genre = requestContent.getReguestParameterValue(GENRE_PARAMETER);
            GenreLogic genreLogic = new GenreLogic();
            boolean isAdded = genreLogic.addGenre(genre);
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
