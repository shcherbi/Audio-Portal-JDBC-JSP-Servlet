package by.scherbakov.audioportal.logic;

import by.scherbakov.audioportal.dao.CommentDAO;
import by.scherbakov.audioportal.entity.Comment;
import by.scherbakov.audioportal.exception.LogicException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Class {@code CommentLogic} is a service class used to connect commands
 * with CommentDAO.
 *
 * @author ScherbakovIlia
 */

public class CommentLogic {
    private static final Logger LOGGER = LogManager.getLogger(CommentLogic.class);

    private static final String DATE_PATTERN = "YYYY-MM-dd";
    private static final String ERROR_ADD_MESSAGE = "message.trackInfo.addCommentError";

    /**
     * Add comment. Transfers to CommentDAO.
     *
     * @param login   is user's login
     * @param idTrack is track's id
     * @param text    is text that user written
     * @param date    is date when comment was written
     * @return empty string if adding was successfully, string with message otherwise
     */
    public String addComment(String login, int idTrack, String text, Date date) {
        String message = null;
        try {
            if (idTrack <= 0 || login == null || login.isEmpty() || text == null || text.isEmpty()) {
                throw new LogicException();
            }
            CommentDAO commentDAO = new CommentDAO();
            SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
            String formatDate = format.format(date);
            message = commentDAO.addComment(login, idTrack, text, formatDate) ? "" : ERROR_ADD_MESSAGE;
            LOGGER.log(Level.INFO, "Add new comment");
        } catch (LogicException e) {
            LOGGER.error("Invalid parameters");
            message = ERROR_ADD_MESSAGE;
        }
        return message;
    }

    /**
     * Retrieved comment to audio track by track id. Transfers to CommentDAO.
     *
     * @param idTrack is track's id
     * @return collection of comments
     */
    public List<Comment> takeAllCommentsById(int idTrack) {
        List<Comment> comments = null;
        CommentDAO commentDAO = null;
        try {
            if (idTrack <= 0) {
                throw new LogicException();
            }
            commentDAO = new CommentDAO();
            comments = commentDAO.findCommentsByTrackId(idTrack);
            LOGGER.log(Level.INFO, "Retrieved all comment by audio track id");
        } catch (LogicException e) {
            LOGGER.error("Invalid id of audio track. idTrack=null or empty");
        }
        return comments;
    }
}
