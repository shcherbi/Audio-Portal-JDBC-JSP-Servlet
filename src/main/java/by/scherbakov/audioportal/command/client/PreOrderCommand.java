package by.scherbakov.audioportal.command.client;

import by.scherbakov.audioportal.command.ActionCommand;
import by.scherbakov.audioportal.entity.AudioTrack;
import by.scherbakov.audioportal.entity.User;
import by.scherbakov.audioportal.logic.OrderLogic;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PreOrderCommand implements ActionCommand {
    private static final String SIGN_IN_ATTRIBUTE = "isSignIn";
    private static final String SIGN_IN_VALUE = "true";
    private static final String LOGIN_PAGE = "path.page.login";
    private static final String ORDER_LIST_ATTRIBUTE = "orderList";
    private static final String USER_ATTRIBUTE = "user";
    private static final String TOTAL_PRICE_ATTRIBUTE = "totalPrice";
    private static final String TOTAL_PRICE_BONUS_ATTRIBUTE = "totalPriceBonus";
    private static final String PRE_ORDER_PAGE = "path.page.preOrder";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        String isSignIn = (String) requestContent.getSessionAttributeValue(SIGN_IN_ATTRIBUTE);
        if(SIGN_IN_VALUE.equals(isSignIn)) {
            User user = (User) requestContent.getSessionAttributeValue(USER_ATTRIBUTE);
            Set orderList = (HashSet) requestContent.getSessionAttributeValue(ORDER_LIST_ATTRIBUTE);
            OrderLogic orderLogic = new OrderLogic();
            BigDecimal totalPrice = orderLogic.calculateTotalPrice(orderList);
            String bonus = user.getBonus();
            if (bonus != null) {
                BigDecimal totalPriceBonus = orderLogic.calculateTotalPriceBonus(bonus, totalPrice);
                requestContent.setRequestAttributeValue(TOTAL_PRICE_BONUS_ATTRIBUTE, totalPriceBonus);
            }
            requestContent.setRequestAttributeValue(TOTAL_PRICE_ATTRIBUTE, totalPrice);
            page = ConfigurationManager.getProperty(PRE_ORDER_PAGE);
        }else {
            page = ConfigurationManager.getProperty(LOGIN_PAGE);
        }
        return page;
    }
}
