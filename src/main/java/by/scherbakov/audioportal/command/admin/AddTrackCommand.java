package by.scherbakov.audioportal.command.admin;

import by.scherbakov.audioportal.command.ActionCommand;
import by.scherbakov.audioportal.entity.AudioTrack;
import by.scherbakov.audioportal.logic.AudioTrackLogic;
import by.scherbakov.audioportal.manager.MessageManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

import java.math.BigDecimal;

public class AddTrackCommand implements ActionCommand {
    private static final String NAME_PARAMETER = "name";
    private static final String ARTIST_PARAMETER = "artist";
    private static final String ALBUM_PARAMETER = "album";
    private static final String STUDIO_PARAMETER = "studio";
    private static final String DATE_PARAMETER = "date";
    private static final String GENRE_PARAMETER = "genre";
    private static final String PRICE_PARAMETER = "price";
    private static final String LINK_PARAMETER = "link";
    private static final String IMAGE_LINK_PARAMETER = "imageLink";
    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String MISTAKE_ATTRIBUTE = "mistakeSongName";
    private static final String MAIN_PAGE_ACTION = "/web?command=main";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String name = requestContent.getReguestParameterValue(NAME_PARAMETER);
        String artist = requestContent.getReguestParameterValue(ARTIST_PARAMETER);
        String album = requestContent.getReguestParameterValue(ALBUM_PARAMETER);
        String studio = requestContent.getReguestParameterValue(STUDIO_PARAMETER);
        String date = requestContent.getReguestParameterValue(DATE_PARAMETER);
        String genre = requestContent.getReguestParameterValue(GENRE_PARAMETER);
        BigDecimal price = new BigDecimal
                (requestContent.getReguestParameterValue(PRICE_PARAMETER));
        String link = requestContent.getReguestParameterValue(LINK_PARAMETER);
        String imageLink = requestContent.getReguestParameterValue(IMAGE_LINK_PARAMETER);
        AudioTrackLogic audioTrackLogic = new AudioTrackLogic();
        String message = audioTrackLogic.addTrack(name, artist, album, studio, date, genre, price, link, imageLink);
        if (!message.isEmpty()) {
            String errorMessage = MessageManager.getMessage(message,
                    (String) requestContent.getSessionAttributeValue(LOCALE_ATTRIBUTE));
            requestContent.setRequestAttributeValue(MISTAKE_ATTRIBUTE, errorMessage);
        }
        page = MAIN_PAGE_ACTION;
        return page;
    }
}