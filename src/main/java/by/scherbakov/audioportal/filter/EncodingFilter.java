package by.scherbakov.audioportal.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(filterName ="EncodingFilter",
        initParams = {@WebInitParam(name = "encoding", value = "UTF-8", description = "Setting WebApp encoding")})
public class EncodingFilter implements Filter {
    private static final String ENCODING_PARAMETER="encoding";
    private String encode;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encode = filterConfig.getInitParameter(ENCODING_PARAMETER);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        String currentEncode=servletRequest.getCharacterEncoding();
        if(currentEncode != null &&!currentEncode.equalsIgnoreCase(encode)){
            servletRequest.setCharacterEncoding(encode);
            servletResponse.setCharacterEncoding(encode);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        encode = null;
    }
}
