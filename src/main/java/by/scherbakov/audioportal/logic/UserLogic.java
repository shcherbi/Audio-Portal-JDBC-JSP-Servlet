package by.scherbakov.audioportal.logic;

import by.scherbakov.audioportal.dao.UserDAO;
import by.scherbakov.audioportal.entity.User;
import by.scherbakov.audioportal.exception.LogicException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Class {@code UserLogic} is a service class used to connect commands
 * with UserDAO.
 *
 * @author ScherbakovIlia
 */

public class UserLogic {
    private static final Logger LOGGER = LogManager.getLogger(UserLogic.class);

    private static final String ROLE_CLIENT_VALUE = "client";
    private static final String ROLE_ADMIN_VALUE = "admin";
    private static final String ERROR_ADD_MESSAGE = "message.registration.userAlreadyExist";
    private static final String ERROR_CHANGE_LOGIN = "message.account.loginChangeError";
    private static final String ERROR_CHANGE_PASSWORD = "message.account.passwordChangeError";
    private static final String ERROR_CHANGE_EMAIL = "message.account.emailChangeError";
    private static final String ERROR_CHANGE_ROLE = "message.allClient.roleChangeError";
    private static final String ERROR_SET_BONUS = "message.allClient.bonusSetError";
    private static final String ERROR_DELETE_USER = "message.allClient.deleteUserError";

    /**
     * Retrieved user by login. Transfers to UserDAO.
     *
     * @param login is user's name
     * @return User object
     */
    public User findUser(String login) {
        User user = null;
        try {
            if (login == null || login.isEmpty()) {
                throw new LogicException();
            }
            UserDAO userDAO = new UserDAO();
            user = userDAO.take(login);
            LOGGER.log(Level.INFO, "User retrieved by login");
        } catch (LogicException e) {
            LOGGER.error("Invalid login. Login=null or login is empty");
        }
        return user;
    }

    /**
     * Register new user. Transfers to UserDAO.
     *
     * @param login    is user's login
     * @param password is user's password
     * @param role     is user's role
     * @return empty string if adding was successfully, string with message otherwise
     */
    public String registerUser(String login, String password, String email, String role) {
        String message = null;
        try {
            if (login == null || login.isEmpty() || password == null || password.isEmpty() ||
                    email == null || email.isEmpty()) {
                throw new LogicException();
            }
            if (role == null || role.isEmpty()) {
                role = ROLE_CLIENT_VALUE;
            }
            String cryptedPassword = DigestUtils.md5Hex(password);
            UserDAO userDAO = new UserDAO();
            message = userDAO.addUser(login, cryptedPassword, email, role, null) ? "" : ERROR_ADD_MESSAGE;
            LOGGER.log(Level.INFO, "User has been registered");
        } catch (LogicException e) {
            LOGGER.error("Invalid parameters");
            message = ERROR_ADD_MESSAGE;
        }
        return message;
    }

    /**
     * Change user login. Transfers to UserDAO.
     *
     * @param login    is user's login
     * @param newLogin is entered login
     * @return empty string if adding was successfully, string with message otherwise
     */
    public String changeLogin(String login, String newLogin) {
        String message = null;
        try {
            if (login == null || login.isEmpty() || newLogin == null || newLogin.isEmpty()) {
                throw new LogicException();
            }
            UserDAO userDAO = new UserDAO();
            message = userDAO.updateLogin(login, newLogin) ? "" : ERROR_CHANGE_LOGIN;
            LOGGER.log(Level.INFO, "Login is changed");
        } catch (LogicException e) {
            LOGGER.error("Invalid login or newLogin." +
                    " login=null or login is empty or new login=null or new login is empty");
            message = ERROR_CHANGE_LOGIN;
        }
        return message;
    }

    /**
     * Change user password. Transfers to UserDAO.
     *
     * @param login    is user's login
     * @param password is entered password
     * @return empty string if adding was successfully, string with message otherwise
     */
    public String changePassword(String login, String password) {
        String message = null;
        try {
            if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
                throw new LogicException();
            }
            String cryptedPassword = DigestUtils.md5Hex(password);
            UserDAO userDAO = new UserDAO();
            message = userDAO.updatePassword(login, cryptedPassword) ? "" : ERROR_CHANGE_PASSWORD;
            LOGGER.log(Level.INFO, "Password is changed");
        } catch (LogicException e) {
            LOGGER.error("Invalid login or password." +
                    " login=null or login is empty or password=null or password is empty");
            message = ERROR_CHANGE_PASSWORD;
        }
        return message;
    }

    /**
     * Change user password. Transfers to UserDAO.
     *
     * @param login is user's login
     * @param email is entered email
     * @return empty string if adding was successfully, string with message otherwise
     */
    public String changeEmail(String login, String email) {
        String message = null;
        try {
            if (login == null || login.isEmpty() || email == null || email.isEmpty()) {
                throw new LogicException();
            }
            UserDAO userDAO = new UserDAO();
            message = userDAO.updateEmail(login, email) ? "" : ERROR_CHANGE_EMAIL;
            LOGGER.log(Level.INFO, "Email is changed");
        } catch (LogicException e) {
            LOGGER.error("Invalid login or email." +
                    " login=null or login is empty or email=null or email is empty");
            message = ERROR_CHANGE_EMAIL;
        }
        return message;
    }

    /**
     * Retrieved all users with client role. Transfers to UserDAO.
     *
     * @return collection of users
     */
    public List<User> takeAllClient() {
        UserDAO userDAO = new UserDAO();
        return userDAO.takeAllClients();
    }

    /**
     * Granting user the admin role. Transfers to UserDAO.
     *
     * @param login is user's login
     * @return empty string if adding was successfully, string with message otherwise
     */
    public String grantUser(String login) {
        String message = null;
        try {
            if (login == null || login.isEmpty()) {
                throw new LogicException();
            }
            UserDAO userDAO = new UserDAO();
            User user = userDAO.take(login);
            user.setRole(ROLE_ADMIN_VALUE);
            message = userDAO.update(user) ? "" : ERROR_CHANGE_ROLE;
            LOGGER.log(Level.INFO, "User is granted");
        } catch (LogicException e) {
            LOGGER.error("Invalid login. login = null or login is empty");
            message = ERROR_CHANGE_ROLE;
        }
        return message;
    }

    /**
     * Sets bonus to user. Transfers to UserDAO.
     *
     * @param login is user's login
     * @param bonus is user's bonus
     * @return empty string if adding was successfully, string with message otherwise
     */
    public String setBonus(String login, String bonus) {
        String message = null;
        try {
            if (login == null || login.isEmpty()) {
                throw new LogicException();
            }
            UserDAO userDAO = new UserDAO();
            User user = userDAO.take(login);
            user.setBonus(bonus);
            message = userDAO.update(user) ? "" : ERROR_SET_BONUS;
            LOGGER.log(Level.INFO, "Bonus has been set");
        } catch (LogicException e) {
            LOGGER.error("Invalid login or bonus." +
                    " login=null or login is empty or bonus=null or bonus is empty");
            message = ERROR_SET_BONUS;
        }
        return message;
    }

    /**
     * Delete user by login. Transfers to UserDAO.
     *
     * @param login is user's login
     * @return empty string if adding was successfully, string with message otherwise
     */
    public String deleteUser(String login) {
        String message = null;
        try {
            if (login == null || login.isEmpty()) {
                throw new LogicException();
            }
            UserDAO userDAO = new UserDAO();
            User user = userDAO.take(login);
            message = userDAO.delete(user) ? "" : ERROR_DELETE_USER;
            LOGGER.log(Level.INFO, "User is deleted");
        } catch (LogicException e) {
            LOGGER.error("Invalid login. login=null or login is empty");
            message = ERROR_DELETE_USER;
        }
        return message;
    }
}
