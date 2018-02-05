package by.scherbakov.audioportal.command.client;

import by.scherbakov.audioportal.command.ActionCommand;
import by.scherbakov.audioportal.entity.AudioTrack;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

import java.util.HashSet;
import java.util.Set;

public class OrderListRemoveCommand implements ActionCommand {
    private static final String ORDER_LIST_ATTRIBUTE = "orderList";
    private static final String TRACK_ATTRIBUTE = "track";
    private static final String PRE_ORDER_PAGE = "/web?command=pre_order";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        AudioTrack audioTrack = (AudioTrack) requestContent.getSessionAttributeValue(TRACK_ATTRIBUTE);
        Set orderList = (HashSet) requestContent.getSessionAttributeValue(ORDER_LIST_ATTRIBUTE);
        orderList.remove(audioTrack);
        requestContent.setSessionAttributeValue(ORDER_LIST_ATTRIBUTE,orderList);
        page = PRE_ORDER_PAGE;
        return page;
    }
}
