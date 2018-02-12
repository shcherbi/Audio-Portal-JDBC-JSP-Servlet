package by.scherbakov.audioportal.command;

import by.scherbakov.audioportal.entity.MusicAssembly;
import by.scherbakov.audioportal.logic.MusicAssemblyLogic;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

import java.util.List;

/**
 * Class {@code MusicAssemblyListCommand} is used to view all assemblies
 *
 * @author ScherbakovIlia
 * @see ActionCommand
 */

public class MusicAssemblyListCommand implements ActionCommand {
    private static final String SIGN_IN_ATTRIBUTE = "isSignIn";
    private static final String SIGN_IN_VALUE = "true";
    private static final String LOGIN_PAGE = "path.page.login";
    private static final String ASSEMBLIES_ATTRIBUTE = "assemblyList";
    private static final String ASSEMBLY_PAGE = "path.page.assemblyList";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String isSignIn = (String) requestContent.getSessionAttributeValue(SIGN_IN_ATTRIBUTE);
        if (SIGN_IN_VALUE.equals(isSignIn)) {
            List<MusicAssembly> musicAssemblies;
            MusicAssemblyLogic musicAssemblyLogic = new MusicAssemblyLogic();
            musicAssemblies = musicAssemblyLogic.takeAllAssemblies();
            requestContent.setRequestAttributeValue(ASSEMBLIES_ATTRIBUTE, musicAssemblies);
            page = ConfigurationManager.getProperty(ASSEMBLY_PAGE);
        } else {
            page = ConfigurationManager.getProperty(LOGIN_PAGE);
        }
        return page;
    }
}
