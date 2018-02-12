package by.scherbakov.audioportal.logic;

import by.scherbakov.audioportal.dao.AudioTrackDAO;
import by.scherbakov.audioportal.dao.MusicAssemblyDAO;
import by.scherbakov.audioportal.entity.AudioTrack;
import by.scherbakov.audioportal.entity.MusicAssembly;
import by.scherbakov.audioportal.exception.LogicException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Class {@code MusicAssemblyLogic} is a service class used to connect commands
 * with MusicAssemblyDAO.
 *
 * @author ScherbakovIlia
 */

public class MusicAssemblyLogic {
    private static final Logger LOGGER = LogManager.getLogger(MusicAssemblyLogic.class);

    private static final String ERROR_ADD_TRACK_ASSEMBLY = "message.trackInfo.addAssemblyError";
    private static final String ERROR_DELETE_ASSEMBLY = "message.assembly.deleteAssemblyError";

    /**
     * Retrieved music assemblies. Transfers to MusicAssemblyDAO.
     *
     * @return collection of music assemblies
     */
    public List<MusicAssembly> takeAllAssemblies() {
        MusicAssemblyDAO musicAssemblyDAO = new MusicAssemblyDAO();
        return musicAssemblyDAO.takeAll();
    }

    /**
     * Add music assembly. Transfers to MusicAssemblyDAO.
     *
     * @param musicAssembly is assembly name
     * @return {@code true} if assembly is added. {@code false} if assembly isn't added.
     */
    public boolean addAssembly(String musicAssembly) {
        boolean isAdded = false;
        try {
            if (musicAssembly == null || musicAssembly.isEmpty()) {
                throw new LogicException();
            }
            MusicAssemblyDAO musicAssemblyDAO = new MusicAssemblyDAO();
            MusicAssembly assembly = musicAssemblyDAO.addAssembly(musicAssembly.trim());
            if (assembly != null) {
                isAdded = true;
            }
            LOGGER.log(Level.INFO, "Add new assembly");
        } catch (LogicException e) {
            LOGGER.error("Invalid parameter.");
        }
        return isAdded;
    }

    /**
     * Add audio track to music assembly. Transfers to MusicAssemblyDAO.
     *
     * @param audioTrack    is track object
     * @param musicAssembly is assembly name
     * @return empty string if adding was successfully, string with message otherwise
     */
    public String addTrackToAssembly(AudioTrack audioTrack, String musicAssembly) {
        String message = null;
        try {
            if (audioTrack == null || musicAssembly == null || musicAssembly.isEmpty()) {
                throw new LogicException();
            }
            MusicAssemblyDAO musicAssemblyDAO = new MusicAssemblyDAO();
            MusicAssembly assembly = musicAssemblyDAO.addAssembly(musicAssembly);
            int idAudioTrack = audioTrack.getId();
            int idAssembly = assembly.getId();
            message = musicAssemblyDAO.addTrackToAssembly(idAssembly, idAudioTrack) ? "" : ERROR_ADD_TRACK_ASSEMBLY;
            LOGGER.log(Level.INFO, "Audio track is added to assembly");
        } catch (LogicException e) {
            LOGGER.error("Invalid parameters.");
            message = ERROR_ADD_TRACK_ASSEMBLY;
        }
        return message;
    }

    /**
     * Delete music assembly. Transfers to MusicAssemblyDAO.
     *
     * @param idMusicAssembly is assembly id
     * @return empty string if adding was successfully, string with message otherwise
     */
    public String deleteAssembly(String idMusicAssembly) {
        String message = null;
        try {
            if (idMusicAssembly == null || idMusicAssembly.isEmpty()) {
                throw new LogicException();
            }
            MusicAssemblyDAO musicAssemblyDAO = new MusicAssemblyDAO();
            MusicAssembly musicAssembly = musicAssemblyDAO.take(idMusicAssembly);
            message = musicAssemblyDAO.delete(musicAssembly) ? "" : ERROR_DELETE_ASSEMBLY;
            LOGGER.log(Level.INFO, "Music assembly is deleted");
        } catch (LogicException e) {
            LOGGER.error("Invalid parameter.");
            message = ERROR_DELETE_ASSEMBLY;
        }
        return message;
    }

    /**
     * Retrieved all tracks from music assembly. Transfers to AudioTrackDAO.
     *
     * @return collection of audio tracks
     */
    public List<AudioTrack> takeAssemblyTrack(String assemblyName) {
        List<AudioTrack> audioTracks = null;
        try {
            if (assemblyName == null || assemblyName.isEmpty()) {
                throw new LogicException();
            }
            AudioTrackDAO audioTrackDAO = new AudioTrackDAO();
            audioTracks = audioTrackDAO.findAssemblyTrackByName(assemblyName);
            LOGGER.log(Level.INFO, "Retrieved track by assembly name");
        } catch (LogicException e) {
            LOGGER.error("Invalid parameter.");
        }
        return audioTracks;
    }
}
