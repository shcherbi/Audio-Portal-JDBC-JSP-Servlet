package by.scherbakov.audioportal.dao;

import by.scherbakov.audioportal.command.ActionFactory;
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

public class UserDAO extends AbstractDAO<User> {
    public static final Logger LOGGER = LogManager.getLogger(UserDAO.class);

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String ROLE = "role";
    private static final String BONUS = "bonus";

    private static final String SQL_SELECT_ALL_USERS = "SELECT login, password, email, role, bonus FROM user ORDER BY login";
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT login, password, email, role, bonus FROM user WHERE login=?";
    private static final String SQL_UPDATE_USER = "UPDATE user SET password=?, email=?, role=?,bonus=? WHERE login=?";
    private static final String SQL_DELETE_USER = "DELETE login, password, email, role, bonus FROM user WHERE login=?";
    private static final String SQL_ADD_USER = "INSERT INTO user(login, password, email, role, bonus) VALUES(?,?,?,?,?)";

   /* private static final String SQL_FIND_PASSWORD_BY_LOGIN = "SELECT password FROM users WHERE nickname=?";
    private static final String SQL_ADD_USER_WITHOUT_CARD = "INSERT INTO users(nickname,password, email) VALUES(?,?,?)";
    private static final String SQL_FIND_USER_BY_EMAIL = "SELECT id, nickname, password, status, money, bonus, card_number, email FROM  users WHERE email=?";
    private static final String SQL_FIND_USER_BY_CARD = "SELECT id, nickname, password, status, money, bonus, card_number, email FROM  users WHERE card_number=?";
    private static final String SQL_CHANGE_LOGIN = "UPDATE users SET nickname=? WHERE id=?";
    private static final String SQL_CHANGE_EMAIL = "UPDATE users SET email=? WHERE id=?";
    private static final String SQL_CHANGE_CARD = "UPDATE users SET card_number=? WHERE id=?";
    private static final String SQL_CHANGE_PASSWORD = "UPDATE users SET password=? WHERE id=?";
    private static final String SQL_CHANGE_MONEY = "UPDATE users SET money=? WHERE id=?";
    private static final String SQL_COMMENTS_NUMBER_BY_ID = "SELECT count(*) FROM comments WHERE user_id=?";
    private static final String SQL_SET_BONUS_BY_NICKNAME = "UPDATE users SET bonus=? WHERE nickname=?";
    private static final String SQL_FIND_BONUS = "SELECT bonus FROM users WHERE nickname=?";
    private static final String SQL_FIND_BONUS_BY_ID = "SELECT bonus FROM users WHERE id=?";
    private static final String SQL_FIND_MONEY_BY_ID = "SELECT money FROM users WHERE id=?";*/

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
                String login = resultSet.getString(LOGIN);
                String password = resultSet.getString(PASSWORD);
                String email = resultSet.getString(EMAIL);
                String role = resultSet.getString(ROLE);
                String bonus = resultSet.getString(BONUS);
                User user = new User(login, password, email, role, bonus);
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
                String password = resultSet.getString(PASSWORD);
                String email = resultSet.getString(EMAIL);
                String role = resultSet.getString(ROLE);
                String bonus = resultSet.getString(BONUS);
                user = new User(login, password, email, role, bonus);
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
    public void update(User user) {
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
            statement.executeUpdate();
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
    }

    @Override
    public void delete(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (user == null) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_DELETE_USER);
            statement.setString(1, user.getLogin());
            statement.executeUpdate();
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
    }

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

    public boolean addUser(String login, String password, String email,String role,String bonus) {
        boolean isAdded = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            if (login == null||login.isEmpty()||password==null||password.isEmpty()||email==null||email.isEmpty()) {
                throw new CommonException();
            }
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SQL_ADD_USER);
            statement.setString(1, login);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.setString(4, role);
            statement.setString(5, bonus);
            if(statement.executeUpdate()!=0){
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
}