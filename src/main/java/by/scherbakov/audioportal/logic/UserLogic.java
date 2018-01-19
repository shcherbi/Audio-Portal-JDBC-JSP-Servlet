package by.scherbakov.audioportal.logic;

import by.scherbakov.audioportal.dao.UserDAO;
import by.scherbakov.audioportal.entity.User;
import by.scherbakov.audioportal.exception.LogicException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserLogic {
    private static final Logger LOGGER = LogManager.getLogger(UserLogic.class);

    public User findUser(String login){
        User user = null;
        try {
            if(login == null || login.isEmpty()){
                throw new LogicException();
            }
            UserDAO userDAO = new UserDAO();
            user=userDAO.get(login);
            LOGGER.log(Level.INFO, "User retrieved by login");
        }catch (LogicException e){
            LOGGER.error("Invalid login. Login=null or login is empty");
        }
        return user;
    }
}
