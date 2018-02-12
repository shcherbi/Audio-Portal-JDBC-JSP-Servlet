package by.scherbakov.audioportal.dao;

import by.scherbakov.audioportal.database.ConnectionPool;
import by.scherbakov.audioportal.entity.Order;
import by.scherbakov.audioportal.exception.CommonException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class {@code OrderDAO} is used to connect with data base.
 * Does all actions related with orders.
 *
 * @author ScherbakovIlia
 * @see AbstractDAO
 */

public class OrderDAO implements AbstractDAO<Order> {
    public static final Logger LOGGER = LogManager.getLogger(OrderDAO.class);

    private static final String ORDER_ID = "idOrder";
    private static final String LOGIN = "login";
    private static final String CARD_NUMBER = "card_number";
    private static final String SVC_CODE = "svc_code";
    private static final String DATE = "date";
    private static final String AUDIO_TRACK_ID = "idAudio_Track";

    private static final String SQL_SELECT_ALL_ORDERS = "SELECT idOrder, login, card_number, svc_code, `order`.date FROM order";
    private static final String SQL_FIND_ORDER_BY_ID = "SELECT idOrder, login, card_number, svc_code, `order`.date FROM order WHERE idOrder=?";
    private static final String SQL_UPDATE_ORDER = "UPDATE order SET login=?, card_number=?, svc_code=?, `order`.date=? WHERE idOrder=?";
    private static final String SQL_DELETE_ORDER = "DELETE FROM `audio_portal`.order WHERE idOrder=?";

    private static final String SQL_SELECT_ORDER_LIST_BY_LOGIN = "SELECT idAudio_Track FROM order_list\n" +
            "JOIN `audio_portal`.order\n" +
            "ON order_list.idOrder = `audio_portal`.order.idOrder\n" +
            "WHERE login = ?";
    private static final String SQL_ADD_ORDER = "INSERT INTO `audio_portal`.order(login, card_number, svc_code, `order`.date) VALUES(?,?,?,?)";
    private static final String SQL_ADD_TRACK_TO_ORDER_LIST = "INSERT INTO order_list(idOrder,idAudio_Track) VALUES(?,?)";

    @Override
    public List<Order> takeAll() {
        List<Order> orders = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_ORDERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = createOrder(resultSet);
                orders.add(order);
            }
            LOGGER.log(Level.INFO, "Received all orders from the database");
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to take all orders", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return orders;
    }

    @Override
    public Order take(String id) {
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
                order = createOrder(resultSet);
            }
            LOGGER.log(Level.INFO, "Received order from the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter. id=null or id is empty!", e);
        } catch (NumberFormatException e) {
            LOGGER.error("Unable to convert string to integer", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to take order", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return order;
    }

    @Override
    public boolean update(Order order) {
        boolean isUpdated = false;
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
            if (statement.executeUpdate() != 0) {
                isUpdated = true;
            }
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
        return isUpdated;
    }

    @Override
    public boolean delete(Order order) {
        boolean isDeleted = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (order == null) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_DELETE_ORDER);
            statement.setInt(1, order.getId());
            if (statement.executeUpdate() != 0) {
                isDeleted = true;
            }
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
        return isDeleted;
    }

    /**
     * Create order
     *
     * @param resultSet is data from database
     * @return Order object
     * @see Order
     */
    private Order createOrder(ResultSet resultSet) throws SQLException {
        Order order = null;
        try {
            if (resultSet == null) {
                throw new CommonException();
            }
            int id = resultSet.getInt(ORDER_ID);
            String login = resultSet.getString(LOGIN);
            String cardNumber = resultSet.getString(CARD_NUMBER);
            String SVCCode = resultSet.getString(SVC_CODE);
            Date date = resultSet.getDate(DATE);
            order = new Order(id, login, cardNumber, SVCCode, date);
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter.", e);
        }
        return order;
    }

    /**
     * Retrieved ordered audio tracks id by login
     *
     * @param login is user's id
     * @return collection of tracks id
     * @see Order
     */
    public List<Integer> findIdTrackByLogin(String login) {
        List<Integer> idTracks = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (login == null || login.isEmpty()) {
                throw new CommonException();
            }
            idTracks = new ArrayList<>();
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_SELECT_ORDER_LIST_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                idTracks.add(resultSet.getInt(AUDIO_TRACK_ID));
            }
            LOGGER.log(Level.INFO, "Received ordered idAudioTracks from the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter. login=null or login is empty!", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to find all ordered idAudioTracks", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return idTracks;
    }

    /**
     * Add order to database
     *
     * @param login      is user's login
     * @param cardNumber is number of client card
     * @param svcCode    is security code of client card
     * @param date       is date when client make order
     * @return Order object
     * @see Order
     */
    public Order addOrder(String login, String cardNumber, String svcCode, String date) {
        Order order = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (login == null || login.isEmpty() || cardNumber == null || cardNumber.isEmpty() ||
                    svcCode == null || svcCode.isEmpty() || date == null || date.isEmpty()) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_ADD_ORDER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, login);
            statement.setString(2, cardNumber);
            statement.setString(3, svcCode);
            statement.setString(4, date);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            Date currentDate = new Date();
            order = new Order(resultSet.getInt(1), login, cardNumber, svcCode, currentDate);
            LOGGER.log(Level.INFO, "Add order to the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter.", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to add order", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return order;
    }

    /**
     * Add track to order
     *
     * @param idOrder      is order id
     * @param idAudioTrack is track's id
     * @see Order
     */
    public void addTrackToOrderList(int idOrder, int idAudioTrack) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (idOrder <= 0 || idAudioTrack <= 0) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_ADD_TRACK_TO_ORDER_LIST);
            statement.setInt(1, idOrder);
            statement.setInt(2, idAudioTrack);
            statement.executeUpdate();
            LOGGER.log(Level.INFO, "Add track to order list to the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter.", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to add track to order list", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
    }
}
