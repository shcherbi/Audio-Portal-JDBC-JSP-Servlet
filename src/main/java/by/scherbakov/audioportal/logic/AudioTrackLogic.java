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

import java.math.BigDecimal;
import java.util.List;


public class AudioTrackLogic {
    private static final Logger LOGGER = LogManager.getLogger(AudioTrackLogic.class);
    private static final String ERROR_ADD_MESSAGE="message.main.trackAlreadyExist";

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

    public List<Comment> takeAllCommentsById(int idTrack) {
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

    public List<AudioTrack> takeOrderedTracks(String login) {
        List<AudioTrack> audioTracks = null;
        try{
            if (login == null || login.isEmpty()) {
                throw new LogicException();
            }
            AudioTrackDAO audioTrackDAO = new AudioTrackDAO();
            audioTracks = audioTrackDAO.findOrderedTracksByLogin(login);
            LOGGER.log(Level.INFO, "Retrieved track by audio track name");
        } catch(LogicException e){
            LOGGER.error("Invalid name of audio track. name=null or empty");
        }
        return audioTracks;
    }

    public String addTrack(String trackName, String artist, String albumName, String studio, String date,
                           String genreName, BigDecimal price, String link, String imageLink){
        String message = null;
        try {
            if(trackName==null||trackName.isEmpty()||artist==null||artist.isEmpty()||albumName==null||albumName.isEmpty()||
                    studio==null||studio.isEmpty()||date==null||date.isEmpty()||genreName==null||genreName.isEmpty()||
                    link==null||link.isEmpty()||imageLink==null||imageLink.isEmpty()){
                throw new LogicException();
            }
            AudioTrackDAO audioTrackDAO = new AudioTrackDAO();
            AlbumDAO albumDAO = new AlbumDAO();
            GenreDAO genreDAO = new GenreDAO();
            Album album = albumDAO.addAlbum(albumName,studio,date);
            Genre genre = genreDAO.addGenre(genreName);
            message = audioTrackDAO.addAudioTrack(trackName,artist,album.getId(),genre.getId(),price,link,imageLink)?"":ERROR_ADD_MESSAGE;
            LOGGER.log(Level.INFO, "Add new audio track");
        } catch (LogicException e) {
            LOGGER.error("Invalid parameters.");
        }
        return message;
    }

}
