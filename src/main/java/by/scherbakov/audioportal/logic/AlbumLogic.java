package by.scherbakov.audioportal.logic;

import by.scherbakov.audioportal.dao.AlbumDAO;
import by.scherbakov.audioportal.entity.Album;
import by.scherbakov.audioportal.exception.LogicException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class {@code AlbumLogic} is a service class used to connect commands
 * with AlbumDAO.
 *
 * @author ScherbakovIlia
 */

public class AlbumLogic {
    private static final Logger LOGGER = LogManager.getLogger(AlbumLogic.class);

    /**
     * Add album. Transfers to AlbumDAO.
     *
     * @param albumName is album's name
     * @param studio    is studio where record
     * @param date      is date when album recorded
     * @return {@code true} if album is added. {@code false} if album isn't added
     */
    public boolean addAlbum(String albumName, String studio, String date) {
        boolean isAdded = false;
        try {
            if (albumName == null || albumName.isEmpty() || studio == null ||
                    studio.isEmpty() || date == null || date.isEmpty()) {
                throw new LogicException();
            }
            AlbumDAO albumDAO = new AlbumDAO();
            Album album = albumDAO.addAlbum(albumName.trim(), studio.trim(), date.trim());
            if (album != null) {
                isAdded = true;
            }
            LOGGER.log(Level.INFO, "Add new album");
        } catch (LogicException e) {
            LOGGER.error("Invalid parameters.");
        }
        return isAdded;
    }

    /**
     * Updates album. Transfers to AlbumDAO.
     *
     * @param albumName is album's name
     * @param studio    is studio where record
     * @param date      is date when album recorded
     * @return updated Album object
     */
    public Album updateAlbum(String albumName, String studio, String date) {
        Album album = null;
        try {
            if (albumName == null || albumName.isEmpty() || studio == null ||
                    studio.isEmpty() || date == null || date.isEmpty()) {
                throw new LogicException();
            }
            AlbumDAO albumDAO = new AlbumDAO();
            album = albumDAO.addAlbum(albumName.trim(), studio.trim(), date.trim());
            LOGGER.log(Level.INFO, "Album is up to date ");
        } catch (LogicException e) {
            LOGGER.error("Invalid parameters.");
        }
        return album;
    }

    /**
     * Retrieved album by id. Transfers to AlbumDAO.
     *
     * @param idAlbum is album's id
     * @return Album object
     */
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
}
