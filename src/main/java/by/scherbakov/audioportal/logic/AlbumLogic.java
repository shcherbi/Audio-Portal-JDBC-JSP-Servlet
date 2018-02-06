package by.scherbakov.audioportal.logic;

import by.scherbakov.audioportal.dao.AlbumDAO;
import by.scherbakov.audioportal.entity.Album;
import by.scherbakov.audioportal.exception.LogicException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AlbumLogic {
    private static final Logger LOGGER = LogManager.getLogger(AlbumLogic.class);

    public boolean addAlbum(String albumName, String studio, String date){
        try {
            if(albumName==null||albumName.isEmpty()||studio==null||studio.isEmpty()||date==null||date.isEmpty()){
                throw new LogicException();
            }
            AlbumDAO albumDAO = new AlbumDAO();
            Album album = albumDAO.addAlbum(albumName,studio,date);
            if(album == null){
                return false;
            }
            LOGGER.log(Level.INFO, "Add new album");
        } catch (LogicException e) {
            LOGGER.error("Invalid parameters.");
        }
        return true;
    }

    public Album updateAlbum(String albumName, String studio, String date){
        Album album = null;
        try {
            if(albumName==null||albumName.isEmpty()||studio==null||studio.isEmpty()||date==null||date.isEmpty()){
                throw new LogicException();
            }
            AlbumDAO albumDAO = new AlbumDAO();
            album = albumDAO.addAlbum(albumName,studio,date);
            LOGGER.log(Level.INFO, "Album is up to date ");
        } catch (LogicException e) {
            LOGGER.error("Invalid parameters.");
        }
        return album;
    }
}
