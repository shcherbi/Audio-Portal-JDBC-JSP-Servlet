package by.scherbakov.audioportal.logic;

import by.scherbakov.audioportal.dao.AlbumDAO;
import by.scherbakov.audioportal.dao.AudioTrackDAO;
import by.scherbakov.audioportal.dao.GenreDAO;
import by.scherbakov.audioportal.entity.Album;
import by.scherbakov.audioportal.entity.AudioTrack;
import by.scherbakov.audioportal.entity.Genre;
import by.scherbakov.audioportal.exception.LogicException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class {@code AudioTrackLogic} is a service class used to connect commands
 * with AudioTrackDAO.
 *
 * @author ScherbakovIlia
 */

public class AudioTrackLogic {
    private static final Logger LOGGER = LogManager.getLogger(AudioTrackLogic.class);

    private static final String ERROR_ADD_MESSAGE = "message.main.trackAddError";
    private static final String ERROR_UPDATE_TRACK_MESSAGE = "message.trackInfo.trackUpdateError";
    private static final String ERROR_DELETE_TRACK_MESSAGE = "message.trackInfo.trackDeleteError";

    /**
     * Retrieved audio track by id. Transfers to AudioTrackDAO.
     *
     * @param idTrack is track's id
     * @return AudioTrack object
     */
    public AudioTrack takeTrackById(String idTrack) {
        AudioTrack audioTrack = null;
        try {
            if (idTrack == null || idTrack.isEmpty()) {
                throw new LogicException();
            }
            AudioTrackDAO audioTrackDAO = new AudioTrackDAO();
            audioTrack = audioTrackDAO.take(idTrack);
            LOGGER.log(Level.INFO, "Retrieved track by audio track id");
        } catch (LogicException e) {
            LOGGER.error("Invalid id of audio track. idTrack=null or empty");
        }
        return audioTrack;
    }

    /**
     * Retrieved all audio tracks. Transfers to AudioTrackDAO.
     *
     * @return collection of audio tracks
     */
    public List<AudioTrack> takeAllTrack() {
        AudioTrackDAO audioTrackDAO = new AudioTrackDAO();
        return audioTrackDAO.takeAll();
    }

    /**
     * Retrieved audio track by name. Transfers to AudioTrackDAO.
     *
     * @param name is track's name
     * @return AudioTrack object
     */
    public AudioTrack takeTrackByName(String name) {
        AudioTrack audioTrack = null;
        try {
            if (name == null || name.isEmpty()) {
                throw new LogicException();
            }
            AudioTrackDAO audioTrackDAO = new AudioTrackDAO();
            audioTrack = audioTrackDAO.findAudioTrackByName(name);
            LOGGER.log(Level.INFO, "Retrieved track by audio track name");
        } catch (LogicException e) {
            LOGGER.error("Invalid name of audio track. name=null or empty");
        }
        return audioTrack;
    }

    /**
     * Retrieved audio track ordered by user. Transfers to AudioTrackDAO.
     *
     * @param login is user's name
     * @return collection of audio tracks
     */
    public List<AudioTrack> takeOrderedTracks(String login) {
        List<AudioTrack> audioTracks = null;
        try {
            if (login == null || login.isEmpty()) {
                throw new LogicException();
            }
            AudioTrackDAO audioTrackDAO = new AudioTrackDAO();
            audioTracks = audioTrackDAO.findOrderedTracksByLogin(login);
            LOGGER.log(Level.INFO, "Retrieved ordered tracks by login");
        } catch (LogicException e) {
            LOGGER.error("Invalid login. login=null or empty");
        }
        return audioTracks;
    }

    /**
     * Add audio track
     *
     * @param trackName is track's name
     * @param artist    is artist of track
     * @param albumName is album's name
     * @param studio    is studio where record
     * @param date      is date when album recorded
     * @param genreName is genre name
     * @param price     is track's price
     * @param link      is link to audio track
     * @param imageLink is link to image for audio track
     * @return empty string if adding was successfully, string with message otherwise
     */
    public String addTrack(String trackName, String artist, String albumName, String studio, String date,
                           String genreName, String price, String link, String imageLink) {
        String message = null;
        try {
            if (trackName == null || trackName.isEmpty() || artist == null || artist.isEmpty() || albumName == null ||
                    albumName.isEmpty() || studio == null || studio.isEmpty() || date == null || date.isEmpty() ||
                    genreName == null || genreName.isEmpty() || link == null || link.isEmpty() || imageLink == null ||
                    imageLink.isEmpty()) {
                throw new LogicException();
            }
            AudioTrackDAO audioTrackDAO = new AudioTrackDAO();
            AlbumDAO albumDAO = new AlbumDAO();
            GenreDAO genreDAO = new GenreDAO();
            Album album = albumDAO.addAlbum(albumName, studio, date);
            Genre genre = genreDAO.addGenre(genreName);
            if(album==null||genre==null){
                throw new LogicException();
            }
            BigDecimal currentPrice = new BigDecimal(price);
            message = audioTrackDAO.addAudioTrack(trackName.trim(), artist.trim(), album.getId(),
                    genre.getId(), currentPrice, link.trim(), imageLink.trim()) ? "" : ERROR_ADD_MESSAGE;
            LOGGER.log(Level.INFO, "Add new audio track");
        } catch (LogicException e) {
            LOGGER.error("Invalid parameters.");
            message = ERROR_ADD_MESSAGE;
        } catch (NumberFormatException e) {
            LOGGER.error("Error while parse string to BigDecimal.");
            message = ERROR_ADD_MESSAGE;
        }
        return message;
    }

    /**
     * Updates audio track
     *
     * @param audioTrack is track object
     * @return empty string if updating was successfully, string with message otherwise
     */
    public String updateAudioTrack(AudioTrack audioTrack) {
        String message = null;
        try {
            if (audioTrack == null) {
                throw new LogicException();
            }
            AudioTrackDAO audioTrackDAO = new AudioTrackDAO();
            message = audioTrackDAO.update(audioTrack) ? "" : ERROR_UPDATE_TRACK_MESSAGE;
            LOGGER.log(Level.INFO, "Audio track is up to date");
        } catch (LogicException e) {
            LOGGER.error("Invalid parameter.");
            message = ERROR_UPDATE_TRACK_MESSAGE;
        }
        return message;
    }

    /**
     * Delete audio track
     *
     * @param audioTrack is track object
     * @return empty string if updating was successfully, string with message otherwise
     */
    public String deleteAudioTrack(AudioTrack audioTrack) {
        String message = null;
        try {
            if (audioTrack == null) {
                throw new LogicException();
            }
            AudioTrackDAO audioTrackDAO = new AudioTrackDAO();
            message = audioTrackDAO.delete(audioTrack) ? "" : ERROR_DELETE_TRACK_MESSAGE;
            LOGGER.log(Level.INFO, "Audio track is deleted");
        } catch (LogicException e) {
            LOGGER.error("Invalid parameter.");
            message = ERROR_DELETE_TRACK_MESSAGE;
        }
        return message;
    }

    /**
     * Convert from string to BigDecimal
     *
     * @param price is track's price
     * @return formatted price
     */
    public BigDecimal takeConvertPrice(String price) {
        BigDecimal currentPrice = null;
        try {
            currentPrice = new BigDecimal(price);
        } catch (NumberFormatException e) {
            LOGGER.error("Error while parse string to BigDecimal.");
        }
        return currentPrice;
    }
}
