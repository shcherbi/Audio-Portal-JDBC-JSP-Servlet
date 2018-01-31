package by.scherbakov.audioportal.logic;

import by.scherbakov.audioportal.dao.CommentDAO;
import by.scherbakov.audioportal.exception.LogicException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommentLogic {
    private static final Logger LOGGER = LogManager.getLogger(CommentLogic.class);
    private static final String ERROR_ADD_MESSAGE="message.trackInfo.addError";

    public String addComment(String login, int idTrack, String text,String date) {
        String message=null;
        try {
            if (idTrack<=0||login==null||login.isEmpty()||text==null||text.isEmpty()) {
                throw new LogicException();
            }
            CommentDAO commentDAO = new CommentDAO();
            message = commentDAO.addComment(login,idTrack,text,date)?"":ERROR_ADD_MESSAGE;
            LOGGER.log(Level.INFO, "Add new comment");
        } catch (LogicException e) {
            LOGGER.error("Invalid parameters");
        }
        return message;
    }
}
