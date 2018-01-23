package by.scherbakov.audioportal.logic;

import by.scherbakov.audioportal.dao.AudioTrackDAO;
import by.scherbakov.audioportal.entity.AudioTrack;
import java.util.List;


public class AudioTrackLogic {

    public List<AudioTrack> takeAllTrack(){
        AudioTrackDAO audioTrackDAO = new AudioTrackDAO();
        return audioTrackDAO.getAll();
    }
}
