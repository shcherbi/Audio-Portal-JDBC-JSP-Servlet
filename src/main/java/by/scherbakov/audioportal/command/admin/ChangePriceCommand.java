package by.scherbakov.audioportal.command.admin;

import by.scherbakov.audioportal.command.ActionCommand;
import by.scherbakov.audioportal.entity.AudioTrack;
import by.scherbakov.audioportal.entity.User;
import by.scherbakov.audioportal.logic.AudioTrackLogic;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.manager.MessageManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

import java.math.BigDecimal;

public class ChangePriceCommand implements ActionCommand {
    private static final String USER_ATTRIBUTE = "user";
    private static final String ADMIN_ROLE = "admin";
    private static final String LOGIN_PAGE = "path.page.login";
    private static final String MAIN_PAGE_ACTION = "/web?command=main";
    private static final String TRACK_ATTRIBUTE = "track";
    private static final String PRICE_PARAMETER = "price";
    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String MISTAKE_ATTRIBUTE = "updateAudioTrackError";
    private static final String TRACK_PAGE_ACTION = "/web?command=track_info&track=";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        User user = (User) requestContent.getSessionAttributeValue(USER_ATTRIBUTE);
        if (user == null) {
            page = ConfigurationManager.getProperty(LOGIN_PAGE);
        } else if (ADMIN_ROLE.equals(user.getRole())) {
            AudioTrack track = (AudioTrack) requestContent.getSessionAttributeValue(TRACK_ATTRIBUTE);
            String price = requestContent.getReguestParameterValue(PRICE_PARAMETER);
            track.setPrice(new BigDecimal(price));
            AudioTrackLogic audioTrackLogic = new AudioTrackLogic();
            String message = audioTrackLogic.updateAudioTrack(track);
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
