package by.scherbakov.audioportal.logic;

import by.scherbakov.audioportal.dao.UserDAO;
import by.scherbakov.audioportal.entity.User;
import by.scherbakov.audioportal.exception.LogicException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserLogic {
    private static final Logger LOGGER = LogManager.getLogger(UserLogic.class);

    private static final String ROLE_CLIENT_VALUE = "client";
    private static final String ERROR_ADD_MESSAGE="message.registration.userAlreadyExist";

    public User findUser(String login){
        User user = null;
        try {
            if(login == null || login.isEmpty()){
                throw new LogicException();
            }
            UserDAO userDAO = new UserDAO();
            user=userDAO.take(login);
            LOGGER.log(Level.INFO, "User retrieved by login");
        }catch (LogicException e){
            LOGGER.error("Invalid login. Login=null or login is empty");
        }
        return user;
    }

    public String registerUser(String login,String password, String email,String role){
        String message = null;
        try {
            if(login == null || login.isEmpty()||password==null||password.isEmpty()||email==null||email.isEmpty()){
                throw new LogicException();
            }
            if (role==null||role.isEmpty()){
                role=ROLE_CLIENT_VALUE;
            }
            String cryptedPassword = DigestUtils.md5Hex(password);
            UserDAO userDAO = new UserDAO();
            message=userDAO.addUser(login,cryptedPassword,email,role,null)?"":ERROR_ADD_MESSAGE;
            LOGGER.log(Level.INFO, "User registered");
        }catch (LogicException e){
            LOGGER.error("Invalid login or password or email." +
                    " Login=null or login is empty or password=null or password is empty" +
                    "or email=null or email is empty");
        }
        return message;
    }
}
