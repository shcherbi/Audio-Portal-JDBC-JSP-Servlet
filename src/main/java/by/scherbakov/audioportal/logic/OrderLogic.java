package by.scherbakov.audioportal.logic;

import by.scherbakov.audioportal.dao.OrderDAO;
import by.scherbakov.audioportal.entity.AudioTrack;
import by.scherbakov.audioportal.entity.Order;
import by.scherbakov.audioportal.exception.LogicException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class {@code OrderLogic} is a service class used to connect commands
 * with OrderDAO.
 *
 * @author ScherbakovIlia
 */

public class OrderLogic {
    private static final Logger LOGGER = LogManager.getLogger(OrderLogic.class);

    private static final String DATE_PATTERN = "YYYY-MM-dd";

    /**
     * Check if a track is ordered. Transfers to OrderDAO.
     *
     * @param login is user's login
     * @param idTrack is track's id
     * @return {@code true} if track is ordered. {@code false} if track isn't ordered.
     */
    public boolean isOrdered(String login, int idTrack) {
        try {
            if (login == null || login.isEmpty() || idTrack <= 0) {
                throw new LogicException();
            }
            OrderDAO orderDAO = new OrderDAO();
            List<Integer> idTracks = orderDAO.findIdTrackByLogin(login);
            for (int i = 0; i < idTracks.size(); i++) {
                if (idTracks.get(i) == idTrack) {
                    LOGGER.log(Level.INFO, "Audio track is already ordered");
                    return true;
                }
            }
            LOGGER.log(Level.INFO, "Audio track isn't ordered");
        } catch (LogicException e) {
            LOGGER.error("Invalid login or idTrack. Login=null or login is empty or idTrack out of range");
        }
        return false;
    }

    /**
     * Add order list of audio tracks. Transfers to OrderDAO.
     *
     * @param login is user's name
     * @param cardNumber is number of client card
     * @param svcCode    is security code of client card
     * @param date       is date when client make order
     * @param orderList  is list of track that user wants to order
     * @return {@code true} if order list is added. {@code false} if order list isn't added.
     */
    public boolean addOrderList(String login, String cardNumber, String svcCode, Date date, Set orderList) {
        try {
            if (login == null || login.isEmpty() || cardNumber == null || cardNumber.isEmpty() ||
                    svcCode == null || svcCode.isEmpty()) {
                throw new LogicException();
            }
            OrderDAO orderDAO = new OrderDAO();
            String cryptedCardNumber = DigestUtils.md5Hex(cardNumber);
            String cryptedSVC = DigestUtils.md5Hex(svcCode);
            SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
            String formatDate = format.format(date);
            Order order = orderDAO.addOrder(login, cryptedCardNumber, cryptedSVC, formatDate);
            int idOrder = order.getId();
            Iterator orderListIterator = orderList.iterator();
            while (orderListIterator.hasNext()) {
                AudioTrack audioTrack = (AudioTrack) orderListIterator.next();
                int idAudioTrack = audioTrack.getId();
                orderDAO.addTrackToOrderList(idOrder, idAudioTrack);
            }
            LOGGER.log(Level.INFO, "Audio tracks are ordered");
        } catch (LogicException e) {
            LOGGER.error("Invalid parameters.");
            return false;
        }
        return true;
    }

    /**
     * Calculate price of track.
     *
     * @param orderList  is list of track that user wants to order
     * @return price.
     */
    public BigDecimal calculateTotalPrice(Set orderList) {
        BigDecimal totalPrice = new BigDecimal(0);
        try {
            if (orderList == null) {
                throw new LogicException();
            }
            Iterator<AudioTrack> iterator = orderList.iterator();
            while (iterator.hasNext()) {
                totalPrice = totalPrice.add(iterator.next().getPrice());
            }
        } catch (LogicException e) {
            LOGGER.log(Level.INFO, "Price has been calculated");
            LOGGER.error("Invalid parameter.");
        }
        return totalPrice;
    }

    /**
     * Calculate discounted price of track.
     *
     * @param bonus  is user's discount
     * @param totalPrice  is track's price
     * @return discounted price.
     */
    public BigDecimal calculateTotalPriceBonus(String bonus, BigDecimal totalPrice) {
        BigDecimal totalPriceBonus = new BigDecimal(0);
        try {
            if (bonus == null || bonus.isEmpty() || totalPrice == null) {
                throw new LogicException();
            }
            int bonusValue = Integer.parseInt(bonus);
            double newTotalPrice = totalPrice.doubleValue() - (totalPrice.doubleValue() * bonusValue / 100);
            totalPriceBonus = new BigDecimal(newTotalPrice);
            totalPriceBonus = totalPriceBonus.setScale(2, RoundingMode.UP);
            LOGGER.log(Level.INFO, "Price with discount has been calculated");
        } catch (LogicException e) {
            LOGGER.error("Invalid parameters.");
        }
        return totalPriceBonus;
    }
}
