package by.scherbakov.audioportal.dao;

import by.scherbakov.audioportal.database.ConnectionPool;
import by.scherbakov.audioportal.entity.Album;
import by.scherbakov.audioportal.entity.AudioTrack;
import by.scherbakov.audioportal.exception.CommonException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AudioTrackDAO extends AbstractDAO<AudioTrack> {
    public static final Logger LOGGER = LogManager.getLogger(AudioTrackDAO.class);

    private static final String AUDIO_TRACK_ID = "idAudio_Track";
    private static final String NAME = "name";
    private static final String ARTIST = "artist";
    private static final String ALBUM_ID = "idAlbum";
    private static final String GENRE_ID = "idGenre";
    private static final String PRICE = "price";
    private static final String LINK = "link";
    private static final String IMAGE_LINK = "image_link";

    private static final String SQL_SELECT_ALL_AUDIO_TRACK = "SELECT idAudio_Track, `audio_track`.name, artist, idAlbum, idGenre, price, link, image_link FROM audio_track";
    private static final String SQL_FIND_AUDIO_TRACK_BY_ID = "SELECT idAudio_Track, `audio_track`.name, artist, idAlbum, idGenre, price, link, image_link FROM audio_track WHERE idAudio_Track=?";
    private static final String SQL_UPDATE_AUDIO_TRACK = "UPDATE audio_track SET `audio_track`.name=?, artist=?, idAlbum=?, idGenre=?, price=?, link=?, image_link=? WHERE idAudio_Track=?";
    private static final String SQL_DELETE_AUDIO_TRACK = "DELETE idAudio_Track, `audio_track`.name, artist, idAlbum, idGenre, price, link, image_link FROM audio_track WHERE idAudio_Track=?";

    private static final String SQL_FIND_AUDIO_TRACK_BY_NAME = "SELECT idAudio_Track, `audio_track`.name, artist, idAlbum, idGenre, price, link, image_link FROM audio_track WHERE `audio_track`.name=?";

    @Override
    public List<AudioTrack> takeAll() {
        List<AudioTrack> audioTracks = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_AUDIO_TRACK);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(AUDIO_TRACK_ID);
                String trackName = resultSet.getString(NAME);
                String artist = resultSet.getString(ARTIST);
                int idAlbum = resultSet.getInt(ALBUM_ID);
                int idGenre = resultSet.getInt(GENRE_ID);
                BigDecimal price = resultSet.getBigDecimal(PRICE);
                String link = resultSet.getString(LINK);
                String imageLink = resultSet.getString(IMAGE_LINK);
                AudioTrack audioTrack = new AudioTrack(id, trackName, artist, idAlbum, idGenre, price, link,imageLink);
                audioTracks.add(audioTrack);
            }
            LOGGER.log(Level.INFO, "Received all audio tracks from the database");
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to take all audio tracks", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return audioTracks;
    }

    @Override
    public AudioTrack take(String id) {
        AudioTrack audioTrack = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (id == null || id.isEmpty()) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_AUDIO_TRACK_BY_ID);
            int idAudioTrack = Integer.parseInt(id);
            statement.setInt(1, idAudioTrack);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String trackName = resultSet.getString(NAME);
                String artist = resultSet.getString(ARTIST);
                int idAlbum = resultSet.getInt(ALBUM_ID);
                int idGenre = resultSet.getInt(GENRE_ID);
                BigDecimal price = resultSet.getBigDecimal(PRICE);
                String link = resultSet.getString(LINK);
                String imageLink = resultSet.getString(IMAGE_LINK);
                audioTrack = new AudioTrack(idAudioTrack, trackName, artist, idAlbum, idGenre, price, link, imageLink);
            }
            LOGGER.log(Level.INFO, "Received audio track from the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter. id=null or id is empty!", e);
        } catch (NumberFormatException e) {
            LOGGER.error("Unable to convert string to integer", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to take audio track", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return audioTrack;
    }

    @Override
    public void update(AudioTrack audioTrack) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (audioTrack == null) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_UPDATE_AUDIO_TRACK);
            statement.setString(1, audioTrack.getName());
            statement.setString(2, audioTrack.getArtist());
            statement.setInt(3, audioTrack.getIdAlbum());
            statement.setInt(4, audioTrack.getIdGenre());
            statement.setBigDecimal(5, audioTrack.getPrice());
            statement.setString(6, audioTrack.getLinkPath());
            statement.setString(7, audioTrack.getImagePath());
            statement.setInt(8, audioTrack.getId());
            statement.executeUpdate();
            LOGGER.log(Level.INFO, "Updated audio track in the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter. audioTrack=null!", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to update audio track", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
    }

    @Override
    public void delete(AudioTrack audioTrack) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (audioTrack == null) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_DELETE_AUDIO_TRACK);
            statement.setInt(1, audioTrack.getId());
            statement.executeUpdate();
            LOGGER.log(Level.INFO, "Deleted audio track in the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter. audioTrack=null!", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to delete audio track", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
    }

    public AudioTrack findAudioTrackByName(String trackName) {
        Connection connection = null;
        PreparedStatement statement = null;
        AudioTrack audioTrack=null;
        try {
            if (trackName==null||trackName.isEmpty()) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_AUDIO_TRACK_BY_NAME);
            statement.setString(1, trackName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int idAudioTrack = resultSet.getInt(AUDIO_TRACK_ID);
                String artist = resultSet.getString(ARTIST);
                int idAlbum = resultSet.getInt(ALBUM_ID);
                int idGenre = resultSet.getInt(GENRE_ID);
                BigDecimal price = resultSet.getBigDecimal(PRICE);
                String link = resultSet.getString(LINK);
                String imageLink = resultSet.getString(IMAGE_LINK);
                audioTrack = new AudioTrack(idAudioTrack, trackName, artist, idAlbum, idGenre, price, link, imageLink);
            }
            LOGGER.log(Level.INFO, "Take audio track from the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter.", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to find audio track", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return audioTrack;
    }
}
