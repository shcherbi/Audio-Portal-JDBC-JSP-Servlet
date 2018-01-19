package by.scherbakov.audioportal.dao;

import by.scherbakov.audioportal.database.ConnectionPool;
import by.scherbakov.audioportal.entity.Comment;
import by.scherbakov.audioportal.entity.Genre;
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

public class GenreDAO extends AbstractDAO<Genre> {
    public static final Logger LOGGER = LogManager.getLogger(GenreDAO.class);

    private static final String GENRE_ID = "idGenre";
    private static final String GENRE = "genre";

    private static final String SQL_SELECT_ALL_GENRES = "SELECT idGenre, genre FROM genre";
    private static final String SQL_FIND_GENRE_BY_ID = "SELECT idGenre, genre FROM genre WHERE idGenre=?";
    private static final String SQL_UPDATE_GENRE = "UPDATE genre SET genre=? WHERE idGenre=?";
    private static final String SQL_DELETE_GENRE = "DELETE idGenre, genre FROM genre WHERE idGenre=?";

    @Override
    public List<Genre> getAll() {
        List<Genre> genres = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_GENRES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(GENRE_ID);
                String trackGenre = resultSet.getString(GENRE);
                Genre genre = new Genre(id, trackGenre);
                genres.add(genre);
            }
            LOGGER.log(Level.INFO, "Received all genres from the database");
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to get all genres", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return genres;
    }

    @Override
    public Genre get(String id) {
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
                String trackGenre = resultSet.getString(GENRE);
                genre = new Genre(idGenre, trackGenre);
            }
            LOGGER.log(Level.INFO, "Received genre from the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter. id=null or id is empty!", e);
        } catch (NumberFormatException e) {
            LOGGER.error("Unable to convert string to integer", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to get genre", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return genre;
    }

    @Override
    public void update(Genre genre) {
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
            statement.executeUpdate();
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
    }

    @Override
    public void delete(Genre genre) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (genre == null) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_DELETE_GENRE);
            statement.setInt(1, genre.getId());
            statement.executeUpdate();
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
    }
}
