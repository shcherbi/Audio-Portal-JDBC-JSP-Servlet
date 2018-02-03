package by.scherbakov.audioportal.logic;

import by.scherbakov.audioportal.dao.OrderDAO;
import by.scherbakov.audioportal.dao.UserDAO;
import by.scherbakov.audioportal.entity.AudioTrack;
import by.scherbakov.audioportal.entity.Order;
import by.scherbakov.audioportal.exception.CommonException;
import by.scherbakov.audioportal.exception.LogicException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class OrderLogic {
    private static final Logger LOGGER = LogManager.getLogger(OrderLogic.class);

    public boolean isOrdered(String login, int idTrack) {
        try {
            if (login == null || login.isEmpty() || idTrack <= 0) {
                throw new LogicException();
            }
            OrderDAO orderDAO = new OrderDAO();
            List<Integer> idTracks = orderDAO.findIdTrackByLogin(login);
            for (int i = 0; i < idTracks.size(); i++) {
                if (idTracks.get(i) == idTrack) {
                    return true;
                }
            }
        } catch (LogicException e) {
            LOGGER.error("Invalid login or trackName. Login=null or login is empty or idTrack out of range");
        }
        return false;
    }

    public boolean addOrderList(String login, String cardNumber, String svcCode,String date, Set orderList){
        try {
            if (login==null||login.isEmpty()||cardNumber==null||cardNumber.isEmpty()||
                    svcCode==null||svcCode.isEmpty()||date==null||date.isEmpty()) {
                throw new LogicException();
            }
            OrderDAO orderDAO = new OrderDAO();
            String cryptedCardNumber = DigestUtils.md5Hex(cardNumber);
            String cryptedSVC = DigestUtils.md5Hex(svcCode);
            Order order = orderDAO.addOrder(login, cryptedCardNumber, cryptedSVC,date);
            int idOrder = order.getId();
            Iterator orderListIterator = orderList.iterator();
            while (orderListIterator.hasNext()){
                AudioTrack audioTrack = (AudioTrack) orderListIterator.next();
                int idAudioTrack = audioTrack.getId();
                orderDAO.addTrackToOrderList(idOrder,idAudioTrack);
            }
        } catch (LogicException e) {
            LOGGER.error("Invalid parameters");
            return false;
        }
        return true;
    }
}
