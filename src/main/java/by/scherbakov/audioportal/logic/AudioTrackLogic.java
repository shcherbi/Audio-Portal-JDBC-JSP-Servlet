package by.scherbakov.audioportal.logic;

import by.scherbakov.audioportal.dao.AlbumDAO;
import by.scherbakov.audioportal.dao.AudioTrackDAO;
import by.scherbakov.audioportal.dao.CommentDAO;
import by.scherbakov.audioportal.dao.GenreDAO;
import by.scherbakov.audioportal.entity.Album;
import by.scherbakov.audioportal.entity.AudioTrack;
import by.scherbakov.audioportal.entity.Comment;
import by.scherbakov.audioportal.entity.Genre;
import by.scherbakov.audioportal.exception.LogicException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class AudioTrackLogic {
    private static final Logger LOGGER = LogManager.getLogger(AudioTrackLogic.class);

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

    public Album takeAlbumById(String idAlbum) {
        Album album = null;
        try {
            if (idAlbum == null || idAlbum.isEmpty()) {
                throw new LogicException();
            }
            AlbumDAO albumDAO = new AlbumDAO();
            album = albumDAO.take(idAlbum);
            LOGGER.log(Level.INFO, "Retrieved album by album id");
        } catch (LogicException e) {
            LOGGER.error("Invalid id of album. idAlbum=null or empty");
        }
        return album;
    }

    public Genre takeGenreById(String idGenre) {
        Genre genre = null;
        try {
            if (idGenre == null || idGenre.isEmpty()) {
                throw new LogicException();
            }
            GenreDAO genreDAO = new GenreDAO();
            genre = genreDAO.take(idGenre);
            LOGGER.log(Level.INFO, "Retrieved genre by genre id");
        } catch (LogicException e) {
            LOGGER.error("Invalid id of genre. idGenre=null or empty");
        }
        return genre;
    }

    public List<AudioTrack> takeAllTrack() {
        AudioTrackDAO audioTrackDAO = new AudioTrackDAO();
        return audioTrackDAO.takeAll();
    }

    public List<Comment> findAllCommentsById(int idTrack) {
        List<Comment> comments = null;
        CommentDAO commentDAO = null;
        try {
            if (idTrack <= 0) {
                throw new LogicException();
            }
            commentDAO = new CommentDAO();
            comments = commentDAO.findByTrackId(idTrack);
            LOGGER.log(Level.INFO, "Retrieved comment by audio track id");
        } catch (LogicException e) {
            LOGGER.error("Invalid id of audio track. idTrack=null or empty");
        }
        return comments;
    }
}
