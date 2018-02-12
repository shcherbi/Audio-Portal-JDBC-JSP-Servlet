package by.scherbakov.audioportal.logic;

import by.scherbakov.audioportal.dao.GenreDAO;
import by.scherbakov.audioportal.entity.Genre;
import by.scherbakov.audioportal.exception.LogicException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class {@code GenreLogic} is a service class used to connect commands
 * with GenreDAO.
 *
 * @author ScherbakovIlia
 */

public class GenreLogic {
    private static final Logger LOGGER = LogManager.getLogger(GenreLogic.class);

    /**
     * Add genre. Transfers to GenreDAO.
     *
     * @param genreName is genre name
     * @return {@code true} if genre is added. {@code false} if genre isn't added
     */
    public boolean addGenre(String genreName) {
        boolean isAdded = false;
        try {
            if (genreName == null || genreName.isEmpty()) {
                throw new LogicException();
            }
            GenreDAO genreDAO = new GenreDAO();
            Genre genre = genreDAO.addGenre(genreName.trim());
            if (genre != null) {
                isAdded = true;
            }
            LOGGER.log(Level.INFO, "Add new genre");
        } catch (LogicException e) {
            LOGGER.error("Invalid parameter.");
        }
        return isAdded;
    }

    /**
     * Updates genre. Transfers to GenreDAO.
     *
     * @param genreName is genre name
     * @return updated Genre object
     */
    public Genre updateGenre(String genreName) {
        Genre genre = null;
        try {
            if (genreName == null || genreName.isEmpty()) {
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

    /**
     * Retrieved genre by id. Transfers to GenreDAO.
     *
     * @param idGenre is genre id
     * @return Genre object
     */
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
}
