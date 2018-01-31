package by.scherbakov.audioportal.command;

import by.scherbakov.audioportal.entity.AudioTrack;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

import java.util.HashSet;
import java.util.Set;

public class OrderListAddCommand implements ActionCommand {
    private static final String ORDER_LIST_ATTRIBUTE = "orderList";
    private static final String TRACK_ATTRIBUTE = "track";
    private static final String TRACK_PAGE = "/web?command=track_info&track=";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        AudioTrack audioTrack = (AudioTrack) requestContent.getSessionAttributeValue(TRACK_ATTRIBUTE);
        Set orderList = (HashSet) requestContent.getSessionAttributeValue(ORDER_LIST_ATTRIBUTE);
        orderList.add(audioTrack);
        requestContent.setSessionAttributeValue(ORDER_LIST_ATTRIBUTE,orderList);
        page = TRACK_PAGE+audioTrack.getId();
        return page;
    }
}
