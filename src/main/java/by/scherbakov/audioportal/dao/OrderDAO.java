package by.scherbakov.audioportal.dao;

import by.scherbakov.audioportal.database.ConnectionPool;
import by.scherbakov.audioportal.entity.AudioTrack;
import by.scherbakov.audioportal.entity.Order;
import by.scherbakov.audioportal.exception.CommonException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAO extends AbstractDAO<Order> {
    public static final Logger LOGGER = LogManager.getLogger(OrderDAO.class);

    private static final String ORDER_ID = "idOrder";
    private static final String LOGIN = "login";
    private static final String CARD_NUMBER = "card_number";
    private static final String SVC_CODE = "svc_code";
    private static final String DATE = "date";

    private static final String SQL_SELECT_ALL_ORDERS = "SELECT idOrder, login, card_number, svc_code, `order`.date FROM order";
    private static final String SQL_FIND_ORDER_BY_ID = "SELECT idOrder, login, card_number, svc_code, `order`.date FROM order WHERE idOrder=?";
    private static final String SQL_UPDATE_ORDER = "UPDATE order SET login=?, card_number=?, svc_code=?, `order`.date=? WHERE idOrder=?";
    private static final String SQL_DELETE_ORDER = "DELETE idOrder, login, card_number, svc_code, `order`.date FROM `audio_portal`.order WHERE idOrder=?";

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_ORDERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(ORDER_ID);
                String login = resultSet.getString(LOGIN);
                String cardNumber = resultSet.getString(CARD_NUMBER);
                String SVCCode = resultSet.getString(SVC_CODE);
                Date date = resultSet.getDate(DATE);
                Order order = new Order(id, login, cardNumber, SVCCode, date);
                orders.add(order);
            }
            LOGGER.log(Level.INFO, "Received all orders from the database");
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to get all orders", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return orders;
    }

    @Override
    public Order get(String id) {
        Order order = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (id == null || id.isEmpty()) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_ORDER_BY_ID);
            int idOrder = Integer.parseInt(id);
            statement.setInt(1, idOrder);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String login = resultSet.getString(LOGIN);
                String cardNumber = resultSet.getString(CARD_NUMBER);
                String SVCCode = resultSet.getString(SVC_CODE);
                Date date = resultSet.getDate(DATE);
                order = new Order(idOrder, login, cardNumber, SVCCode, date);
            }
            LOGGER.log(Level.INFO, "Received order from the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter. id=null or id is empty!", e);
        } catch (NumberFormatException e) {
            LOGGER.error("Unable to convert string to integer", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to get order", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return order;
    }

    @Override
    public void update(Order order) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (order == null) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_UPDATE_ORDER);
            statement.setString(1, order.getLogin());
            statement.setString(2, order.getCardNumber());
            statement.setString(3, order.getSVCCode());
            statement.setDate(4, (java.sql.Date) order.getDate());
            statement.setInt(5, order.getId());
            statement.executeUpdate();
            LOGGER.log(Level.INFO, "Updated order in the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter. order=null!", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to update order", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
    }

    @Override
    public void delete(Order order) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (order == null) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_DELETE_ORDER);
            statement.setInt(1, order.getId());
            statement.executeUpdate();
            LOGGER.log(Level.INFO, "Deleted order in the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter. audioTrack=null!", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to delete order", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
    }
}
