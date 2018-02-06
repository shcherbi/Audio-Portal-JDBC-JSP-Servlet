package by.scherbakov.audioportal.command.client;

import by.scherbakov.audioportal.command.ActionCommand;
import by.scherbakov.audioportal.entity.AudioTrack;
import by.scherbakov.audioportal.entity.User;
import by.scherbakov.audioportal.logic.OrderLogic;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.manager.MessageManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

import java.util.HashSet;
import java.util.Set;

public class OrderListAddCommand implements ActionCommand {
    private static final String ORDER_LIST_ATTRIBUTE = "orderList";
    private static final String TRACK_ATTRIBUTE = "track";
    private static final String USER_ATTRIBUTE = "user";
    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String IS_ORDERED_ATTRIBUTE = "isOrdered";
    private static final String TRACK_PAGE = "/web?command=track_info&track=";
    private static final String ADD_ORDER_MESSAGE = "message.trackInfo.addOrderListError";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        AudioTrack audioTrack = (AudioTrack) requestContent.getSessionAttributeValue(TRACK_ATTRIBUTE);
        User user = (User) requestContent.getSessionAttributeValue(USER_ATTRIBUTE);
        OrderLogic orderLogic = new OrderLogic();
        String login = user.getLogin();
        int idAudioTrack = audioTrack.getId();
        if(orderLogic.isOrdered(login,idAudioTrack)){
            String pageMessage = MessageManager.getMessage(ADD_ORDER_MESSAGE,
                    (String) requestContent.getSessionAttributeValue(LOCALE_ATTRIBUTE));
            requestContent.setRequestAttributeValue(IS_ORDERED_ATTRIBUTE, pageMessage);
        }else {
            Set orderList = (HashSet) requestContent.getSessionAttributeValue(ORDER_LIST_ATTRIBUTE);
            orderList.add(audioTrack);
            requestContent.setSessionAttributeValue(ORDER_LIST_ATTRIBUTE,orderList);
        }
        page = TRACK_PAGE+audioTrack.getId();
        return page;
    }
}
