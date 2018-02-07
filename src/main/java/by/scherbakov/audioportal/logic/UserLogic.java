package by.scherbakov.audioportal.logic;

import by.scherbakov.audioportal.dao.AudioTrackDAO;
import by.scherbakov.audioportal.dao.UserDAO;
import by.scherbakov.audioportal.entity.AudioTrack;
import by.scherbakov.audioportal.entity.User;
import by.scherbakov.audioportal.exception.LogicException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserLogic {
    private static final Logger LOGGER = LogManager.getLogger(UserLogic.class);

    private static final String ROLE_CLIENT_VALUE = "client";
    private static final String ROLE_ADMIN_VALUE = "admin";
    private static final String ERROR_ADD_MESSAGE="message.registration.userAlreadyExist";
    private static final String ERROR_CHANGE_LOGIN="message.account.loginChangeError";
    private static final String ERROR_CHANGE_PASSWORD="message.account.passwordChangeError";
    private static final String ERROR_CHANGE_EMAIL="message.account.emailChangeError";
    private static final String ERROR_CHANGE_ROLE="message.allClient.roleChangeError";
    private static final String ERROR_SET_BONUS="message.allClient.bonusSetError";
    private static final String ERROR_DELETE_USER="message.allClient.deleteUserError";

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
            message=ERROR_ADD_MESSAGE;
        }
        return message;
    }

    public String changeLogin(String login,String newLogin){
        String message = null;
        try {
            if(login == null || login.isEmpty()||newLogin==null||newLogin.isEmpty()){
                throw new LogicException();
            }
            UserDAO userDAO = new UserDAO();
            message=userDAO.updateLogin(login,newLogin)?"":ERROR_CHANGE_LOGIN;
            LOGGER.log(Level.INFO, "Login changed");
        }catch (LogicException e){
            LOGGER.error("Invalid login.");
            message=ERROR_CHANGE_LOGIN;
        }
        return message;
    }

    public String changePassword(String login,String password){
        String message = null;
        try {
            if(login == null || login.isEmpty()||password==null||password.isEmpty()){
                throw new LogicException();
            }
            String cryptedPassword = DigestUtils.md5Hex(password);
            UserDAO userDAO = new UserDAO();
            message=userDAO.updatePassword(login,cryptedPassword)?"":ERROR_CHANGE_PASSWORD;
            LOGGER.log(Level.INFO, "Password changed");
        }catch (LogicException e){
            LOGGER.error("Invalid password.");
            message=ERROR_CHANGE_PASSWORD;
        }
        return message;
    }

    public String changeEmail(String login,String email){
        String message = null;
        try {
            if(login == null || login.isEmpty()||email==null||email.isEmpty()){
                throw new LogicException();
            }
            UserDAO userDAO = new UserDAO();
            message=userDAO.updateEmail(login,email)?"":ERROR_CHANGE_EMAIL;
            LOGGER.log(Level.INFO, "Password changed");
        }catch (LogicException e){
            LOGGER.error("Invalid password.");
            message=ERROR_CHANGE_EMAIL;
        }
        return message;
    }

    public List<User> takeAllClient() {
        UserDAO userDAO = new UserDAO();
        return userDAO.takeAllClients();
    }

    public String grantUser(String login){
        String message = null;
        try {
            if(login == null || login.isEmpty()){
                throw new LogicException();
            }
            UserDAO userDAO = new UserDAO();
            User user = userDAO.take(login);
            user.setRole(ROLE_ADMIN_VALUE);
            message=userDAO.update(user)?"":ERROR_CHANGE_ROLE;
            LOGGER.log(Level.INFO, "Password changed");
        }catch (LogicException e){
            LOGGER.error("Invalid password.");
            message=ERROR_CHANGE_ROLE;
        }
        return message;
    }

    public String setBonus(String login,String bonus){
        String message = null;
        try {
            if(login == null || login.isEmpty()){
                throw new LogicException();
            }
            UserDAO userDAO = new UserDAO();
            User user = userDAO.take(login);
            user.setBonus(bonus);
            message=userDAO.update(user)?"":ERROR_SET_BONUS;
            LOGGER.log(Level.INFO, "Password changed");
        }catch (LogicException e){
            LOGGER.error("Invalid password.");
            message=ERROR_SET_BONUS;
        }
        return message;
    }

    public String deleteUser(String login){
        String message = null;
        try {
            if(login == null || login.isEmpty()){
                throw new LogicException();
            }
            UserDAO userDAO = new UserDAO();
            User user = userDAO.take(login);
            message=userDAO.delete(user)?"":ERROR_DELETE_USER;
            LOGGER.log(Level.INFO, "Password changed");
        }catch (LogicException e){
            LOGGER.error("Invalid password.");
            message=ERROR_DELETE_USER;
        }
        return message;
    }
}
