package by.scherbakov.audioportal.dao;

import by.scherbakov.audioportal.database.ConnectionPool;
import by.scherbakov.audioportal.entity.Genre;
import by.scherbakov.audioportal.exception.CommonException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class {@code GenreDAO} is used to connect with data base.
 * Does all actions related with genres.
 *
 * @author ScherbakovIlia
 * @see AbstractDAO
 */

public class GenreDAO implements AbstractDAO<Genre> {
    public static final Logger LOGGER = LogManager.getLogger(GenreDAO.class);

    private static final String GENRE_ID = "idGenre";
    private static final String GENRE = "genre";

    private static final String SQL_SELECT_ALL_GENRES = "SELECT idGenre, genre FROM genre";
    private static final String SQL_FIND_GENRE_BY_ID = "SELECT idGenre, genre FROM genre WHERE idGenre=?";
    private static final String SQL_UPDATE_GENRE = "UPDATE genre SET genre=? WHERE idGenre=?";
    private static final String SQL_DELETE_GENRE = "DELETE FROM genre WHERE idGenre=?";

    private static final String SQL_ADD_GENRE = "INSERT INTO genre(genre) VALUES (?)";

    @Override
    public List<Genre> takeAll() {
        List<Genre> genres = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_GENRES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Genre genre = createGenre(resultSet);
                genres.add(genre);
            }
            LOGGER.log(Level.INFO, "Received all genres from the database");
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to take all genres", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return genres;
    }

    @Override
    public Genre take(String id) {
        Genre genre = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (id == null || id.isEmpty()) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_GENRE_BY_ID);
            int idGenre = Integer.parseInt(id);
            statement.setInt(1, idGenre);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                genre = createGenre(resultSet);
            }
            LOGGER.log(Level.INFO, "Received genre from the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter. id=null or id is empty!", e);
        } catch (NumberFormatException e) {
            LOGGER.error("Unable to convert string to integer", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to take genre", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return genre;
    }

    @Override
    public boolean update(Genre genre) {
        boolean isUpdated = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (genre == null) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_UPDATE_GENRE);
            statement.setString(1, genre.getGenre());
            statement.setInt(2, genre.getId());
            if(statement.executeUpdate()!=0){
                isUpdated = true;
            }
            LOGGER.log(Level.INFO, "Updated genre in the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter. genre=null!", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to update genre", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return isUpdated;
    }

    @Override
    public boolean delete(Genre genre) {
        boolean isDeleted = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (genre == null) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_DELETE_GENRE);
            statement.setInt(1, genre.getId());
            if(statement.executeUpdate()!=0){
                isDeleted = true;
            }
            LOGGER.log(Level.INFO, "Deleted genre in the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter. genre=null!", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to delete genre", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return isDeleted;
    }

    /**
     * Create genre
     *
     * @param resultSet is data from database
     * @return Genre object
     * @see Genre
     */
    private Genre createGenre(ResultSet resultSet) throws SQLException {
        Genre genre = null;
        try {
            if (resultSet == null) {
                throw new CommonException();
            }
            int id = resultSet.getInt(GENRE_ID);
            String trackGenre = resultSet.getString(GENRE);
            genre = new Genre(id, trackGenre);
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter.", e);
        }
        return genre;
    }

    /**
     * Add genre to database
     *
     * @param genreName is genre name
     * @return Genre object
     * @see Genre
     */
    public Genre addGenre(String genreName) {
        Genre genre=null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (genreName == null||genreName.isEmpty()) {
                throw new CommonException();
            }
            genre=checkAndGet(takeAll(),genreName);
            if(genre==null){
                connection = ConnectionPool.getInstance().takeConnection();
                statement = connection.prepareStatement(SQL_ADD_GENRE, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, genreName);
                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                genre = new Genre(resultSet.getInt(1),genreName);
            }
            LOGGER.log(Level.INFO, "Add genre to the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter.", e);
        }catch(SQLException e) {
            LOGGER.error("SQLException in trying to add genre", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return genre;
    }

    /**
     * Check existence of genre and retrieve if exist
     *
     * @param genres    is collection of genres
     * @param genreName    is genre name
     * @return Genre object
     * @see Genre
     */
    private Genre checkAndGet(List<Genre> genres, String genreName){
        for (int i = 0; i<genres.size();i++){
            String currentGenreName= genres.get(i).getGenre();
            if(currentGenreName.equals(genreName)){
                return genres.get(i);
            }
        }
        return null;
    }
}
