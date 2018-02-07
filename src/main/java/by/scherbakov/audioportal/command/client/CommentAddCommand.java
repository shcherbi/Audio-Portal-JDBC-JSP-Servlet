package by.scherbakov.audioportal.command.client;

import by.scherbakov.audioportal.command.ActionCommand;
import by.scherbakov.audioportal.entity.AudioTrack;
import by.scherbakov.audioportal.entity.Comment;
import by.scherbakov.audioportal.entity.User;
import by.scherbakov.audioportal.logic.AudioTrackLogic;
import by.scherbakov.audioportal.logic.CommentLogic;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.manager.MessageManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentAddCommand implements ActionCommand {
    private static final String SIGN_IN_ATTRIBUTE = "isSignIn";
    private static final String SIGN_IN_VALUE = "true";
    private static final String LOGIN_PAGE = "path.page.login";
    private static final String USER_ATTRIBUTE = "user";
    private static final String TRACK_ATTRIBUTE = "track";
    private static final String TEXT_ATTRIBUTE = "text";
    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String MISTAKE_ATTRIBUTE = "mistakeComment";
    private static final String TRACK_PAGE = "/web?command=track_info&track=";
    private static final String DATE_PATTERN = "YYYY-MM-dd";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String isSignIn = (String) requestContent.getSessionAttributeValue(SIGN_IN_ATTRIBUTE);
        if(SIGN_IN_VALUE.equals(isSignIn)) {
            User user = (User) requestContent.getSessionAttributeValue(USER_ATTRIBUTE);
            AudioTrack audioTrack = (AudioTrack) requestContent.getSessionAttributeValue(TRACK_ATTRIBUTE);
            String commentText = requestContent.getReguestParameterValue(TEXT_ATTRIBUTE);
            String login = user.getLogin();
            int idTrack = audioTrack.getId();
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
            String formatDate = format.format(date);
            CommentLogic commentLogic = new CommentLogic();
            String message = commentLogic.addComment(login, idTrack, commentText, formatDate);
            if (!message.isEmpty()) {
                String errorMessage = MessageManager.getMessage(message,
                        (String) requestContent.getSessionAttributeValue(LOCALE_ATTRIBUTE));
                requestContent.setRequestAttributeValue(MISTAKE_ATTRIBUTE, errorMessage);
            }
            page = TRACK_PAGE + audioTrack.getId();
        }else {
            page = ConfigurationManager.getProperty(LOGIN_PAGE);
        }
        return page;
    }
}
