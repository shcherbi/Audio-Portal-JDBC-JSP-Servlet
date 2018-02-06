package by.scherbakov.audioportal.dao;

import by.scherbakov.audioportal.database.ConnectionPool;
import by.scherbakov.audioportal.entity.Album;
import by.scherbakov.audioportal.entity.AudioTrack;
import by.scherbakov.audioportal.exception.CommonException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private static final String SQL_DELETE_AUDIO_TRACK = "DELETE FROM audio_track WHERE idAudio_Track=?";

    private static final String SQL_FIND_AUDIO_TRACK_BY_NAME = "SELECT idAudio_Track, `audio_track`.name, artist, idAlbum, idGenre, price, link, image_link FROM audio_track WHERE `audio_track`.name=?";
    private static final String SQL_FIND_ORDERED_TRACK_BY_LOGIN = "SELECT audio_track.idAudio_Track, `audio_track`.name, artist, idAlbum, idGenre, price, link, image_link FROM order_list\n" +
            "JOIN audio_track\n" +
            "ON order_list.idAudio_Track = audio_track.idAudio_Track\n" +
            "JOIN `order`\n" +
            "ON order_list.idOrder = `order`.idOrder\n" +
            "WHERE `order`.login = ?\n";
    private static final String SQL_ADD_AUDIO_TRACK = "INSERT INTO audio_track(`audio_track`.name, artist, idAlbum, idGenre, price, link, image_link) VALUES (?,?,?,?,?,?,?)";

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
                AudioTrack audioTrack = createAudioTrack(resultSet);
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
                audioTrack = createAudioTrack(resultSet);
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
    public boolean update(AudioTrack audioTrack) {
        boolean isUpdated = false;
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
            if(statement.executeUpdate()!=0){
                isUpdated = true;
            }
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
        return isUpdated;
    }

    @Override
    public boolean delete(AudioTrack audioTrack) {
        boolean isDeleted = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (audioTrack == null) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_DELETE_AUDIO_TRACK);
            statement.setInt(1, audioTrack.getId());
            if(statement.executeUpdate()!=0){
                isDeleted = true;
            }
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
        return isDeleted;
    }

    public AudioTrack findAudioTrackByName(String trackName) {
        Connection connection = null;
        PreparedStatement statement = null;
        AudioTrack audioTrack = null;
        try {
            if (trackName == null || trackName.isEmpty()) {
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

    public List<AudioTrack> findOrderedTracksByLogin(String login) {
        List<AudioTrack> audioTracks = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (login == null || login.isEmpty()) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_ORDERED_TRACK_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                AudioTrack audioTrack = createAudioTrack(resultSet);
                audioTracks.add(audioTrack);
            }
            LOGGER.log(Level.INFO, "Received all ordered audio tracks by login from the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter.", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to take all ordered audio tracks by login", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return audioTracks;
    }

    private AudioTrack createAudioTrack(ResultSet resultSet) throws SQLException {
        AudioTrack audioTrack = null;
        try {
            if (resultSet == null) {
                throw new CommonException();
            }
            int id = resultSet.getInt(AUDIO_TRACK_ID);
            String trackName = resultSet.getString(NAME);
            String artist = resultSet.getString(ARTIST);
            int idAlbum = resultSet.getInt(ALBUM_ID);
            int idGenre = resultSet.getInt(GENRE_ID);
            BigDecimal price = resultSet.getBigDecimal(PRICE);
            String link = resultSet.getString(LINK);
            String imageLink = resultSet.getString(IMAGE_LINK);
            audioTrack = new AudioTrack(id, trackName, artist, idAlbum, idGenre, price, link, imageLink);
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter.", e);
        }
        return audioTrack;
    }

    public boolean addAudioTrack(String trackName, String artist, int idAlbum, int idGenre, BigDecimal price,
                                    String link, String imageLink) {
        AudioTrack audioTrack = null;
        Connection connection = null;
        PreparedStatement statement = null;
        boolean isAdded = false;
        try {
            if (trackName == null || trackName.isEmpty() || artist == null || artist.isEmpty() || idAlbum <= 0 || idGenre <= 0 ||
                    link == null || link.isEmpty() || imageLink == null || imageLink.isEmpty()) {
                throw new CommonException();
            }
            audioTrack = checkAndGet(takeAll(), trackName, artist, idAlbum, idGenre, price, link, imageLink);
            if (audioTrack == null) {
                connection = ConnectionPool.getInstance().takeConnection();
                statement = connection.prepareStatement(SQL_ADD_AUDIO_TRACK);
                statement.setString(1, trackName);
                statement.setString(2, artist);
                statement.setInt(3, idAlbum);
                statement.setInt(4, idGenre);
                statement.setBigDecimal(5, price);
                statement.setString(6, link);
                statement.setString(7, imageLink);
                statement.executeUpdate();
                if(statement.executeUpdate()!=0){
                    isAdded = true;
                }
            }
            LOGGER.log(Level.INFO, "Add audio track to the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter.", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to add audio track", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return isAdded;
    }

    private AudioTrack checkAndGet(List<AudioTrack> audioTracks, String trackName, String artist, int idAlbum,
                                   int idGenre, BigDecimal price, String link, String imageLink) {
        for (int i = 0; i < audioTracks.size(); i++) {
            String currentTrackName = audioTracks.get(i).getName();
            String currentArtist = audioTracks.get(i).getArtist();
            int currentIdAlbum = audioTracks.get(i).getIdAlbum();
            int currentIdGenre = audioTracks.get(i).getIdGenre();
            BigDecimal currentPrice = audioTracks.get(i).getPrice();
            String currentLink = audioTracks.get(i).getLinkPath();
            String currentImageLink = audioTracks.get(i).getImagePath();
            if (currentTrackName.equals(trackName) && currentArtist.equals(artist) && currentIdAlbum == idAlbum &&
                    currentIdGenre == idGenre && currentPrice.equals(price) &&
                    currentLink.equals(link) && currentImageLink.equals(imageLink)) {
                return audioTracks.get(i);
            }
        }
        return null;
    }
}
