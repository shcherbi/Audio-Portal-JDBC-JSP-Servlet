package by.scherbakov.audioportal.filter;

import by.scherbakov.audioportal.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter",
        initParams = @WebInitParam(name = "indexPath", value = "/index.jsp"))
public class AdminFilter implements Filter {
    private static final String INDEX_PATH_PARAMETER = "indexPath";
    private static final String USER_ATTRIBUTE = "user";
    private static final String ADMIN_ROLE = "admin";

    private String indexPath;

    @Override
    public void init(FilterConfig filterConfig) {
        indexPath = filterConfig.getInitParameter(INDEX_PATH_PARAMETER);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User) request.getSession().getAttribute(USER_ATTRIBUTE);
        if (!ADMIN_ROLE.equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + indexPath);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        indexPath=null;
    }
}
