package by.scherbakov.audioportal.command.admin;

import by.scherbakov.audioportal.command.ActionCommand;
import by.scherbakov.audioportal.entity.AudioTrack;
import by.scherbakov.audioportal.logic.AudioTrackLogic;
import by.scherbakov.audioportal.manager.MessageManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

public class ChangeLinkCommand implements ActionCommand {
    private static final String TRACK_ATTRIBUTE = "track";
    private static final String LINK_PARAMETER = "link";
    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String MISTAKE_ATTRIBUTE = "updateAudioTrackError";
    private static final String TRACK_PAGE_ACTION = "/web?command=track_info&track=";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        AudioTrack track = (AudioTrack) requestContent.getSessionAttributeValue(TRACK_ATTRIBUTE);
        String link = requestContent.getReguestParameterValue(LINK_PARAMETER);
        track.setLinkPath(link);
        AudioTrackLogic audioTrackLogic = new AudioTrackLogic();
        String message = audioTrackLogic.updateAudioTrack(track);
        if (!message.isEmpty()) {
            String errorMessage = MessageManager.getMessage(message,
                    (String) requestContent.getSessionAttributeValue(LOCALE_ATTRIBUTE));
            requestContent.setRequestAttributeValue(MISTAKE_ATTRIBUTE, errorMessage);
        }
        page = TRACK_PAGE_ACTION+track.getId();
        return page;
    }
}
