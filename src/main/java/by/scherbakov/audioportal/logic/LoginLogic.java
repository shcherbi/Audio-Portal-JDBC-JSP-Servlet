package by.scherbakov.audioportal.logic;

import by.scherbakov.audioportal.dao.UserDAO;
import by.scherbakov.audioportal.exception.LogicException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class {@code LoginLogic} is a service class used to connect commands
 * with UserDAO.
 *
 * @author ScherbakovIlia
 */

public class LoginLogic {
    private static final Logger LOGGER = LogManager.getLogger(LoginLogic.class);

    /**
     * Check user registration status. Transfers to UserDAO.
     *
     * @param login    is user's login
     * @param password is user's password
     * @return {@code true} if user is exist. {@code false} if user isn't exist.
     */
    public boolean checkLogin(String login, String password) {
        boolean checkFlag = false;
        try {
            if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
                throw new LogicException();
            }
            UserDAO userDAO = new UserDAO();
            String cryptedPassword = DigestUtils.md5Hex(password);
            String databasePassword = userDAO.findPasswordByLogin(login);
            if (cryptedPassword.equals(databasePassword)) {
                checkFlag = true;
            }
            LOGGER.log(Level.INFO, "Verified login and password");
        } catch (LogicException e) {
            LOGGER.error("Invalid login or password. Login=null or login is empty or password=null or password is empty");
        }
        return checkFlag;
    }
}
