package by.scherbakov.audioportal.dao;

import by.scherbakov.audioportal.database.ConnectionPool;
import by.scherbakov.audioportal.entity.AudioTrack;
import by.scherbakov.audioportal.entity.Comment;
import by.scherbakov.audioportal.exception.CommonException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class {@code CommentDAO} is used to connect with data base.
 * Does all actions related with comments.
 *
 * @author ScherbakovIlia
 * @see AbstractDAO
 */

public class CommentDAO implements AbstractDAO<Comment> {
    public static final Logger LOGGER = LogManager.getLogger(CommentDAO.class);

    private static final String COMMENT_ID = "idComments";
    private static final String AUDIO_TRACK_ID = "idAudio_Track";
    private static final String LOGIN = "login";
    private static final String TEXT = "text";
    private static final String DATE = "date";

    private static final String SQL_SELECT_ALL_COMMENTS = "SELECT idComments, idAudio_Track, login, text, `comment`.date FROM comment";
    private static final String SQL_FIND_COMMENT_BY_ID = "SELECT idComments, idAudio_Track, login, text, `comment`.date FROM comment WHERE idComments=?";
    private static final String SQL_UPDATE_COMMENT = "UPDATE comment SET idAudio_Track=?, login=?, text=?, `comment`.date=? WHERE idComments=?";
    private static final String SQL_DELETE_COMMENT = "DELETE FROM comment WHERE idComments=?";

    private static final String SQL_FIND_BY_TRACK_ID = "SELECT idComments, idAudio_Track, login, text, `comment`.date FROM comment WHERE idAudio_Track=?";
    private static final String SQL_ADD_COMMENT = "INSERT INTO comment(idAudio_Track,login,text,`comment`.date) VALUES (?,?,?,?)";

    @Override
    public List<Comment> takeAll() {
        List<Comment> comments = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            comments = new ArrayList<>();
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_COMMENTS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Comment comment = createComment(resultSet);
                comments.add(comment);
            }
            LOGGER.log(Level.INFO, "Received all comments from the database");
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to take all comments", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return comments;
    }

    @Override
    public Comment take(String id) {
        Comment comment = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (id == null || id.isEmpty()) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_COMMENT_BY_ID);
            int idComment = Integer.parseInt(id);
            statement.setInt(1, idComment);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                comment = createComment(resultSet);
            }
            LOGGER.log(Level.INFO, "Received comment from the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter. id=null or id is empty!", e);
        } catch (NumberFormatException e) {
            LOGGER.error("Unable to convert string to integer", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to take comment", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return comment;
    }

    @Override
    public boolean update(Comment comment) {
        boolean isUpdated = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (comment == null) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_UPDATE_COMMENT);
            statement.setInt(1, comment.getIdAudioTrack());
            statement.setString(2, comment.getLogin());
            statement.setString(3, comment.getText());
            statement.setDate(4, (java.sql.Date) comment.getDate());
            statement.setInt(5, comment.getId());
            if(statement.executeUpdate()!=0){
                isUpdated = true;
            }
            LOGGER.log(Level.INFO, "Updated comment in the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter. comment=null!", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to update comment", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return isUpdated;
    }

    @Override
    public boolean delete(Comment comment) {
        boolean isDeleted = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (comment == null) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_DELETE_COMMENT);
            statement.setInt(1, comment.getId());
            if(statement.executeUpdate()!=0){
                isDeleted = true;
            }
            LOGGER.log(Level.INFO, "Deleted comment in the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter. comment=null!", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to delete comment", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return isDeleted;
    }

    /**
     * Create comment
     *
     * @param resultSet is data from database
     * @return Comment object
     * @see Comment
     */
    private Comment createComment(ResultSet resultSet) throws SQLException {
        Comment comment = null;
        try {
            if (resultSet == null) {
                throw new CommonException();
            }
            int id = resultSet.getInt(COMMENT_ID);
            int idAudioTrack = resultSet.getInt(AUDIO_TRACK_ID);
            String login = resultSet.getString(LOGIN);
            String text = resultSet.getString(TEXT);
            Date date = resultSet.getDate(DATE);
            comment = new Comment(id, idAudioTrack, login, text, date);
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter.", e);
        }
        return comment;
    }

    /**
     * Retrieve comments by id audio track
     *
     * @param idTrack is track's id
     * @return collection of comments
     * @see Comment
     */
    public List<Comment> findCommentsByTrackId(int idTrack) {
        List<Comment> comments=null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (idTrack <= 0) {
                throw new CommonException();
            }
            comments = new ArrayList<>();
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_BY_TRACK_ID);
            statement.setInt(1, idTrack);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt(COMMENT_ID);
                String login = resultSet.getString(LOGIN);
                String text = resultSet.getString(TEXT);
                Date date = resultSet.getDate(DATE);
                Comment comment = new Comment(id, idTrack, login, text, date);
                comments.add(comment);
            }
            LOGGER.log(Level.INFO, "Find comment in the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter. idTrack out of range", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to find comment by audio track id", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return comments;
    }

    /**
     * Add comment to database
     *
     * @param login is user's login
     * @param idTrack is track's id
     * @param text   is text of comment
     * @param date   is date when comment was written
     * @return {@code true} if comment is added. {@code false}  if comment isn't added.
     * @see AudioTrack
     */
    public boolean addComment(String login, int idTrack, String text,String date) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean isAdded = false;
        try {
            if (idTrack<=0||login==null||login.isEmpty()||text==null||text.isEmpty()) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_ADD_COMMENT);
            statement.setInt(1,idTrack);
            statement.setString(2,login);
            statement.setString(3,text);
            statement.setString(4,date);
            if(statement.executeUpdate()!=0){
                isAdded = true;
            }
            LOGGER.log(Level.INFO, "Add comment to the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter.", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to add comment", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return isAdded;
    }
}
