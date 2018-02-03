package by.scherbakov.audioportal.command;

import by.scherbakov.audioportal.entity.User;
import by.scherbakov.audioportal.logic.OrderLogic;
import by.scherbakov.audioportal.manager.MessageManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class OrderCommand implements ActionCommand {
    private static final String ORDER_LIST_ATTRIBUTE = "orderList";
    private static final String USER_ATTRIBUTE = "user";
    private static final String CARD_NUMBER_PARAMETER = "cardNumber";
    private static final String SVC_PARAMETER = "svcCode";
    private static final String DATE_PATTERN = "YYYY-MM-dd";
    private static final String LOCALE_ATTRIBUTE = "local";
    private static final String PURCHASE_REJECTED_ATTRIBUTE = "purchaseRejected";
    private static final String PRE_ORDER_PAGE = "/web?command=pre_order";
    private static final String ADD_ORDER_MESSAGE = "message.preOrder.purchaseError";
    private static final String MAIN_PAGE = "/web?command=main";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        User user = (User) requestContent.getSessionAttributeValue(USER_ATTRIBUTE);
        String login = user.getLogin();
        String cardNumber = requestContent.getReguestParameterValue(CARD_NUMBER_PARAMETER);
        String svcCode = requestContent.getReguestParameterValue(SVC_PARAMETER);
        Set orderList = (HashSet) requestContent.getSessionAttributeValue(ORDER_LIST_ATTRIBUTE);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
        String formatDate = format.format(date);
        OrderLogic orderLogic = new OrderLogic();
        if (orderLogic.addOrderList(login, cardNumber, svcCode, formatDate, orderList)) {
            orderList.clear();
            requestContent.setSessionAttributeValue(ORDER_LIST_ATTRIBUTE, orderList);
            page = MAIN_PAGE;
        } else {
            String errorMessage = MessageManager.getMessage(ADD_ORDER_MESSAGE,
                    (String) requestContent.getSessionAttributeValue(LOCALE_ATTRIBUTE));
            requestContent.setRequestAttributeValue(PURCHASE_REJECTED_ATTRIBUTE, errorMessage);
            page = PRE_ORDER_PAGE;
        }
        return page;
    }
}
