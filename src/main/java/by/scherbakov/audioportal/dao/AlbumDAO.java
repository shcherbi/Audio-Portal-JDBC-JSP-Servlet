package by.scherbakov.audioportal.dao;

import by.scherbakov.audioportal.database.ConnectionPool;
import by.scherbakov.audioportal.entity.Album;
import by.scherbakov.audioportal.exception.CommonException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlbumDAO extends AbstractDAO<Album> {
    public static final Logger LOGGER = LogManager.getLogger(AlbumDAO.class);

    private static final String ALBUM_ID = "idAlbum";
    private static final String ALBUM_NAME = "album_name";
    private static final String STUDIO = "studio";
    private static final String DATE = "date";
    private static final String DATE_PATTERN = "YYYY-MM-dd";

    private static final String SQL_SELECT_ALL_ALBUMS = "SELECT idAlbum, album_name, studio, `album`.date FROM album";
    private static final String SQL_FIND_ALBUM_BY_ID = "SELECT idAlbum, album_name, studio, `album`.date FROM album WHERE idAlbum=?";
    private static final String SQL_UPDATE_ALBUM = "UPDATE album SET album_name=?, studio=?, `album`.date=? WHERE idAlbum=?";
    private static final String SQL_DELETE_ALBUM = "DELETE FROM album WHERE idAlbum=?";

    private static final String SQL_ADD_ALBUM = "INSERT INTO album(album_name, studio, `album`.date) VALUES (?,?,?)";

    @Override
    public List<Album> takeAll() {
        List<Album> albums = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_ALBUMS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(ALBUM_ID);
                String albumName = resultSet.getString(ALBUM_NAME);
                String studio = resultSet.getString(STUDIO);
                Date date = resultSet.getDate(DATE);
                Album album = new Album(id, albumName, studio, date);
                albums.add(album);
            }
            LOGGER.log(Level.INFO, "Received all albums from the database");
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to take all albums", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return albums;
    }

    @Override
    public Album take(String id) {
        Album album = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (id == null || id.isEmpty()) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_ALBUM_BY_ID);
            int idAlbum = Integer.parseInt(id);
            statement.setInt(1, idAlbum);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String albumName = resultSet.getString(ALBUM_NAME);
                String studio = resultSet.getString(STUDIO);
                Date date = resultSet.getDate(DATE);
                album = new Album(idAlbum, albumName, studio, date);
            }
            LOGGER.log(Level.INFO, "Received album from the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter. id=null or id is empty!", e);
        }catch(NumberFormatException e){
            LOGGER.error("Unable to convert string to integer", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to take album", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return album;
    }

    @Override
    public void update(Album album) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (album == null) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_UPDATE_ALBUM);
            statement.setString(1, album.getAlbumName());
            statement.setString(2, album.getStudio());
            statement.setDate(3, (java.sql.Date) album.getDate());
            statement.setInt(4, album.getId());
            statement.executeUpdate();
            LOGGER.log(Level.INFO, "Updated album in the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter. Album=null!", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to update album", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
    }

    @Override
    public void delete(Album album) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (album == null) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_DELETE_ALBUM);
            statement.setInt(1, album.getId());
            statement.executeUpdate();
            LOGGER.log(Level.INFO, "Deleted album in the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter. Album=null!", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to delete album", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
    }

    public Album addAlbum(String albumName, String studio, String date) {
        Album album=null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (albumName == null||albumName.isEmpty()||studio==null||studio.isEmpty()||date==null||date.isEmpty()) {
                throw new CommonException();
            }
            album=checkAndGet(takeAll(),albumName,studio,date);
            if(album==null){
                connection = ConnectionPool.getInstance().takeConnection();
                statement = connection.prepareStatement(SQL_ADD_ALBUM, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, albumName);
                statement.setString(2, studio);
                statement.setString(3, date);
                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
                Date formatDate = format.parse(date);
                album = new Album(resultSet.getInt(1),albumName,studio,formatDate);
            }
            LOGGER.log(Level.INFO, "Add album to the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter.", e);
        }catch (ParseException e){
            LOGGER.error("Error while parse date", e);
        } catch(SQLException e) {
            LOGGER.error("SQLException in trying to add album", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return album;
    }

    private Album checkAndGet(List<Album> albums, String albumName, String studio, String date){
        for (int i = 0; i<albums.size();i++){
            String currentAlbumName = albums.get(i).getAlbumName();
            String currentStudio = albums.get(i).getStudio();
            Date currentDate = albums.get(i).getDate();
            SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
            String formatDate = format.format(date);
            if(currentAlbumName.equals(albumName)&& currentStudio.equals(studio)&&formatDate.equals(date)){
                return albums.get(i);
            }
        }
        return null;
    }
}
