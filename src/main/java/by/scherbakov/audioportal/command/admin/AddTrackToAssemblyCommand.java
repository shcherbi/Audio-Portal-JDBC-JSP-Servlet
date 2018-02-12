package by.scherbakov.audioportal.command.admin;

import by.scherbakov.audioportal.command.ActionCommand;
import by.scherbakov.audioportal.entity.AudioTrack;
import by.scherbakov.audioportal.entity.User;
import by.scherbakov.audioportal.logic.MusicAssemblyLogic;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.manager.MessageManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

/**
 * Class {@code AddTrackToAssemblyCommand} is used to add audio track
 * to music assembly
 *
 * @author ScherbakovIlia
 * @see ActionCommand
 */

public class AddTrackToAssemblyCommand implements ActionCommand {
    private static final String USER_ATTRIBUTE = "user";
    private static final String ADMIN_ROLE = "admin";
    private static final String LOGIN_PAGE = "path.page.login";
    private static final String TRACK_ATTRIBUTE = "track";
    private static final String ASSEMBLY_PARAMETER = "assembly";
    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String MISTAKE_ATTRIBUTE = "updateAudioTrackError";
    private static final String MAIN_PAGE_ACTION = "/web?command=main";
    private static final String TRACK_PAGE_ACTION = "/web?command=track_info&track=";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        User user = (User) requestContent.getSessionAttributeValue(USER_ATTRIBUTE);
        if (user == null) {
            page = ConfigurationManager.getProperty(LOGIN_PAGE);
        } else if (ADMIN_ROLE.equals(user.getRole())) {
            AudioTrack audioTrack = (AudioTrack) requestContent.getSessionAttributeValue(TRACK_ATTRIBUTE);
            String musicAssembly = requestContent.getRequestParameterValue(ASSEMBLY_PARAMETER);
            MusicAssemblyLogic musicAssemblyLogic = new MusicAssemblyLogic();
            String message = musicAssemblyLogic.addTrackToAssembly(audioTrack,musicAssembly);
            if (!message.isEmpty()) {
                String errorMessage = MessageManager.getMessage(message,
                        (String) requestContent.getSessionAttributeValue(LOCALE_ATTRIBUTE));
                requestContent.setRequestAttributeValue(MISTAKE_ATTRIBUTE, errorMessage);
                page = TRACK_PAGE_ACTION + audioTrack.getId();
            } else {
                page = MAIN_PAGE_ACTION;
            }
        }else {
            page = MAIN_PAGE_ACTION;
        }
        return page;
    }
}
