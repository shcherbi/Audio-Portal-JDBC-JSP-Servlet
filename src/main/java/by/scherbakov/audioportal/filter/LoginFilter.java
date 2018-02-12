package by.scherbakov.audioportal.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class {@code LoginFilter} is used to control access to users pages
 *
 * @author ScherbakovIlia
 */
@WebFilter (filterName = "LoginFilter",
        initParams = @WebInitParam(name = "indexPath", value = "/index.jsp"))
public class LoginFilter implements Filter {
    private static final String INDEX_PATH_PARAMETER = "indexPath";
    private static final String SIGN_IN_ATTRIBUTE = "isSignIn";
    private static final String TRUE = "true";

    private String indexPath;

    @Override
    public void init(FilterConfig filterConfig) {
        indexPath=filterConfig.getInitParameter(INDEX_PATH_PARAMETER);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String isSignIn = (String) request.getSession().getAttribute(SIGN_IN_ATTRIBUTE);
        if(!TRUE.equalsIgnoreCase(isSignIn)){
            response.sendRedirect(request.getContextPath()+indexPath);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        indexPath=null;
    }
}
