package by.scherbakov.audioportal.command;

import by.scherbakov.audioportal.entity.*;
import by.scherbakov.audioportal.logic.MusicAssemblyLogic;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

import java.util.List;

/**
 * Class {@code MusicAssemblyInfoCommand} is used to view assembly info
 *
 * @author ScherbakovIlia
 * @see ActionCommand
 */

public class MusicAssemblyInfoCommand implements ActionCommand {
    private static final String SIGN_IN_ATTRIBUTE = "isSignIn";
    private static final String SIGN_IN_VALUE = "true";
    private static final String LOGIN_PAGE = "path.page.login";
    private static final String ASSEMBLY_PARAMETER = "assembly";
    private static final String TRACKS_ATTRIBUTE = "tracks";
    private static final String ASSEMBLY_PAGE = "path.page.assembly";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String isSignIn = (String) requestContent.getSessionAttributeValue(SIGN_IN_ATTRIBUTE);
        if(SIGN_IN_VALUE.equals(isSignIn)) {
            List<AudioTrack> tracks;
            String idAssembly = requestContent.getRequestParameterValue(ASSEMBLY_PARAMETER);
            MusicAssemblyLogic musicAssemblyLogic = new MusicAssemblyLogic();
            tracks = musicAssemblyLogic.takeAssemblyTrack(idAssembly);
            requestContent.setRequestAttributeValue(TRACKS_ATTRIBUTE, tracks);
            page = ConfigurationManager.getProperty(ASSEMBLY_PAGE);
        }else {
            page = ConfigurationManager.getProperty(LOGIN_PAGE);
        }
        return page;
    }
}

