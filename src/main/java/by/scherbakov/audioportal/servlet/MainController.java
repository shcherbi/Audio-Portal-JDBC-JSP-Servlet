package by.scherbakov.audioportal.servlet;

import by.scherbakov.audioportal.command.ActionCommand;
import by.scherbakov.audioportal.command.ActionFactory;
import by.scherbakov.audioportal.database.ConnectionPool;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.manager.MessageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class {@code MainController} is a servlet class used as a controller of the application.
 *
 * @author ScherbakovIlia
 * @see HttpServlet
 */

@WebServlet("/web")
public class MainController extends HttpServlet implements ServletContextListener {
    private static final String LOGIN_PAGE = "path.page.login";
    private static final String NULL_PAGE_ATTRIBUTE = "nullPage";
    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String NULL_PAGE_MESSAGE = "message.login.nullPage";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    }

    /**
     * Close ConnectionPool when servlet destroyed
     *
     * @param servletContextEvent is servlet event
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionPool.getInstance().closePool();
    }

    /**
     * Uses in get requests
     *
     * @param request  is servlet's request
     * @param response is servlet's response
     * @throws ServletException if there are servlet errors
     * @throws IOException      if there are input/output errors
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Uses in post requests
     *
     * @param request  is servlet's request
     * @param response is servlet's response
     * @throws ServletException if there are servlet errors
     * @throws IOException      if there are input/output errors
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Actions after arriving request
     *
     * @param request  is servlet's request
     * @param response is servlet's response
     * @throws ServletException if there are servlet errors
     * @throws IOException      if there are input/output errors
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        SessionRequestContent requestContent = new SessionRequestContent();
        requestContent.extractValues(request);
        ActionCommand actionCommand = ActionFactory.defineCommand(requestContent);
        page = actionCommand.execute(requestContent);
        requestContent.insertValues(request);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getProperty(LOGIN_PAGE);
            String pageMessage = MessageManager.getMessage(NULL_PAGE_MESSAGE,
                    (String) request.getSession().getAttribute(LOCALE_ATTRIBUTE));
            request.getSession().setAttribute(NULL_PAGE_ATTRIBUTE, pageMessage);
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}
