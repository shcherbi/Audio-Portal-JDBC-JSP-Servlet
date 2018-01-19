package by.scherbakov.audioportal.servlet;

import by.scherbakov.audioportal.command.ActionCommand;
import by.scherbakov.audioportal.command.ActionFactory;
import by.scherbakov.audioportal.manager.ConfigurationManager;
import by.scherbakov.audioportal.manager.MessageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/web")
public class MainController extends HttpServlet {
    private static final String LOGIN_PAGE = "path.page.login";
    private static final String NULL_PAGE_ATTRIBUTE = "nullPage";
    private static final String LOCALE_ATTRIBUTE = "locale";
    private static final String NULL_PAGE_MESSAGE = "message.login.nullPage";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

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
