package by.scherbakov.audioportal.command.client;

import by.scherbakov.audioportal.command.ActionCommand;
import by.scherbakov.audioportal.entity.AudioTrack;
import by.scherbakov.audioportal.entity.User;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.servlet.SessionRequestContent;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PreOrderCommand implements ActionCommand {
    private static final String ORDER_LIST_ATTRIBUTE = "orderList";
    private static final String USER_ATTRIBUTE = "user";
    private static final String TOTAL_PRICE_ATTRIBUTE = "totalPrice";
    private static final String TOTAL_PRICE_BONUS_ATTRIBUTE = "totalPriceBonus";
    private static final String PRE_ORDER_PAGE = "path.page.preOrder";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        User user = (User) requestContent.getSessionAttributeValue(USER_ATTRIBUTE);
        Set orderList = (HashSet) requestContent.getSessionAttributeValue(ORDER_LIST_ATTRIBUTE);
        Iterator<AudioTrack> iterator = orderList.iterator();
        BigDecimal totalPrice = new BigDecimal(0);
        while (iterator.hasNext()) {
            totalPrice=totalPrice.add(iterator.next().getPrice());
        }
        String bonus = user.getBonus();
        if(bonus!=null){
            int bonusValue = Integer.parseInt(bonus);
            int newTotalPrice = totalPrice.intValue()-(totalPrice.intValue()*bonusValue/100);
            BigDecimal totalPriceBonus = new BigDecimal(newTotalPrice);
            requestContent.setRequestAttributeValue(TOTAL_PRICE_BONUS_ATTRIBUTE,totalPriceBonus);
        }
        requestContent.setRequestAttributeValue(TOTAL_PRICE_ATTRIBUTE, totalPrice);
        page = ConfigurationManager.getProperty(PRE_ORDER_PAGE);
        return page;
    }
}
