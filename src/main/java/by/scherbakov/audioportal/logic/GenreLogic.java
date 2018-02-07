package by.scherbakov.audioportal.logic;

import by.scherbakov.audioportal.dao.AlbumDAO;
import by.scherbakov.audioportal.dao.GenreDAO;
import by.scherbakov.audioportal.entity.Album;
import by.scherbakov.audioportal.entity.Genre;
import by.scherbakov.audioportal.exception.LogicException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenreLogic {
    private static final Logger LOGGER = LogManager.getLogger(GenreLogic.class);

    public boolean addGenre(String genreName){
        try {
            if(genreName==null||genreName.isEmpty()){
                throw new LogicException();
            }
            GenreDAO genreDAO = new GenreDAO();
            Genre genre = genreDAO.addGenre(genreName.trim());
            if(genre == null){
                return false;
            }
            LOGGER.log(Level.INFO, "Add new genre");
        } catch (LogicException e) {
            LOGGER.error("Invalid parameter.");
        }
        return true;
    }

    public Genre updateGenre(String genreName){
        Genre genre = null;
        try {
            if(genreName==null||genreName.isEmpty()){
                throw new LogicException();
            }
            GenreDAO genreDAO = new GenreDAO();
            genre = genreDAO.addGenre(genreName.trim());
            LOGGER.log(Level.INFO, "Genre is up to date ");
        } catch (LogicException e) {
            LOGGER.error("Invalid parameter.");
        }
        return genre;
    }
}
