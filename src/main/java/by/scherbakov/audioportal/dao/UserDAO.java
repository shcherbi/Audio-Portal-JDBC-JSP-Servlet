package by.scherbakov.audioportal.dao;

import by.scherbakov.audioportal.database.ConnectionPool;
import by.scherbakov.audioportal.entity.User;
import by.scherbakov.audioportal.exception.CommonException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class {@code UserDAO} is used to connect with data base.
 * Does all actions related with users.
 *
 * @author ScherbakovIlia
 * @see AbstractDAO
 */

public class UserDAO implements AbstractDAO<User> {
    public static final Logger LOGGER = LogManager.getLogger(UserDAO.class);

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String ROLE = "role";
    private static final String BONUS = "bonus";

    private static final String SQL_SELECT_ALL_USERS = "SELECT login, password, email, role, bonus FROM user ORDER BY login";
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT login, password, email, role, bonus FROM user WHERE login=?";
    private static final String SQL_UPDATE_USER = "UPDATE user SET password=?, email=?, role=?,bonus=? WHERE login=?";
    private static final String SQL_DELETE_USER = "DELETE FROM user WHERE login=?";

    private static final String SQL_ADD_USER = "INSERT INTO user(login, password, email, role, bonus) VALUES(?,?,?,?,?)";
    private static final String SQL_CHANGE_LOGIN = "UPDATE user SET login=? WHERE login=?";
    private static final String SQL_CHANGE_PASSWORD = "UPDATE user SET password=? WHERE login=?";
    private static final String SQL_CHANGE_EMAIL = "UPDATE user SET email=? WHERE login=?";
    private static final String SQL_SELECT_ALL_CLIENTS = "SELECT login, password, email, role, bonus FROM user WHERE role='client' ORDER BY login";

    @Override
    public List<User> takeAll() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = createUser(resultSet);
                users.add(user);
            }
            LOGGER.log(Level.INFO, "Received all users from the database");
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to take all users", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return users;
    }

    @Override
    public User take(String login) {
        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (login == null || login.isEmpty()) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = createUser(resultSet);
            }
            LOGGER.log(Level.INFO, "Received user from the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter. Login=null or login is empty!", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to take user", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return user;
    }

    @Override
    public boolean update(User user) {
        boolean isUpdated = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (user == null) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_UPDATE_USER);
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getRole());
            statement.setString(4, user.getBonus());
            statement.setString(5, user.getLogin());
            if (statement.executeUpdate() != 0) {
                isUpdated = true;
            }
            LOGGER.log(Level.INFO, "Updated user in the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter. User=null!", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to update user", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return isUpdated;
    }

    @Override
    public boolean delete(User user) {
        boolean isDeleted = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (user == null) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_DELETE_USER);
            statement.setString(1, user.getLogin());
            if (statement.executeUpdate() != 0) {
                isDeleted = true;
            }
            LOGGER.log(Level.INFO, "Deleted user in the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter. User=null!", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to delete user", e);
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
     * @return User object
     * @see User
     */
    private User createUser(ResultSet resultSet) throws SQLException {
        User user = null;
        try {
            if (resultSet == null) {
                throw new CommonException();
            }
            String login = resultSet.getString(LOGIN);
            String password = resultSet.getString(PASSWORD);
            String email = resultSet.getString(EMAIL);
            String role = resultSet.getString(ROLE);
            String bonus = resultSet.getString(BONUS);
            user = new User(login, password, email, role, bonus);
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter.", e);
        }
        return user;
    }

    /**
     * Retrieved password by login
     *
     * @param login is user's login
     * @return password
     * @see User
     */
    public String findPasswordByLogin(String login) {
        String password = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (login == null || login.isEmpty()) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                password = resultSet.getString(PASSWORD);
            }
            LOGGER.log(Level.INFO, "Found a password by login");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameter. Login=null or login is empty!", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to find password by user login", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return password;
    }

    /**
     * Add user to database
     *
     * @param login    is user's login
     * @param password is user's password
     * @param email    is user's email
     * @param role     is user's role
     * @param bonus    is user's bonus
     * @return {@code true} if user is added. {@code false} if user isn't added.
     * @see User
     */
    public boolean addUser(String login, String password, String email, String role, String bonus) {
        boolean isAdded = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (login == null || login.isEmpty() || password == null || password.isEmpty() || email == null || email.isEmpty()) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_ADD_USER);
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.setString(4, role);
            statement.setString(5, bonus);
            if (statement.executeUpdate() != 0) {
                isAdded = true;
            }
            LOGGER.log(Level.INFO, "Added user to the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameters!", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to add user", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return isAdded;
    }

    /**
     * Updates user's login
     *
     * @param login    is user's login
     * @param newLogin is entered login
     * @return {@code true} if user is updated. {@code false} if user isn't updated.
     * @see User
     */
    public boolean updateLogin(String login, String newLogin) {
        boolean isAdded = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (login == null || login.isEmpty()) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_CHANGE_LOGIN);
            statement.setString(1, newLogin);
            statement.setString(2, login);
            if (statement.executeUpdate() != 0) {
                isAdded = true;
            }
            LOGGER.log(Level.INFO, "Updated login in the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameters!", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to update login", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return isAdded;
    }

    /**
     * Updates user's password
     *
     * @param login    is user's login
     * @param password is entered password
     * @return {@code true} if user is updated. {@code false} if user isn't updated.
     * @see User
     */
    public boolean updatePassword(String login, String password) {
        boolean isAdded = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (login == null || login.isEmpty()) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_CHANGE_PASSWORD);
            statement.setString(1, password);
            statement.setString(2, login);
            if (statement.executeUpdate() != 0) {
                isAdded = true;
            }
            LOGGER.log(Level.INFO, "Updated password in the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameters!", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to update password", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return isAdded;
    }

    /**
     * Updates user's email
     *
     * @param login is user's login
     * @param email is entered email
     * @return {@code true} if user is updated. {@code false} if user isn't updated.
     * @see User
     */
    public boolean updateEmail(String login, String email) {
        boolean isAdded = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (login == null || login.isEmpty()) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_CHANGE_EMAIL);
            statement.setString(1, email);
            statement.setString(2, login);
            if (statement.executeUpdate() != 0) {
                isAdded = true;
            }
            LOGGER.log(Level.INFO, "Updated email in the database");
        } catch (CommonException e) {
            LOGGER.error("Invalid parameters!", e);
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to update email", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return isAdded;
    }

    /**
     * Retrieved all clients
     *
     * @return collection of users.
     * @see User
     */
    public List<User> takeAllClients() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_CLIENTS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = createUser(resultSet);
                users.add(user);
            }
            LOGGER.log(Level.INFO, "Received all clients from the database");
        } catch (SQLException e) {
            LOGGER.error("SQLException in trying to take all clients", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().closeConnection(connection);
            }
        }
        return users;
    }
}