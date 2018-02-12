package by.scherbakov.audioportal.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Class {@code SessionRequestContent} is used to get and store all request parameters,
 * session ans request attributes.
 *
 * @author ScherbakovIlia
 * @see HttpServletRequest
 */

public class SessionRequestContent {
    /**
     * Servlet request's attributes
     */
    private Map<String, Object> requestAttributes;
    /**
     * Servlet request's parameters
     */
    private Map<String, String[]> requestParameters;
    /**
     * Servlet request's session attribute
     */
    private Map<String, Object> sessionAttributes;

    /**
     * Store request and session attributes to HashMap
     *
     * @param request is servlet's request
     */
    public void extractValues(HttpServletRequest request) {
        requestParameters = request.getParameterMap();
        Enumeration<String> attributeNames = null;
        requestAttributes = new HashMap<>();
        attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            requestAttributes.put(attributeName, request.getAttribute(attributeName));
        }
        sessionAttributes = new HashMap<>();
        HttpSession session = request.getSession(true);
        attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String name = attributeNames.nextElement();
            sessionAttributes.put(name, session.getAttribute(name));
        }
    }

    /**
     * Sets all request and session attributes to request
     *
     * @param request is servlet's request
     */
    public void insertValues(HttpServletRequest request) {
        for (Map.Entry<String, Object> requestAttribute : requestAttributes.entrySet()) {
            request.setAttribute(requestAttribute.getKey(), requestAttribute.getValue());
        }
        for (Map.Entry<String, Object> sessionAttribute : sessionAttributes.entrySet()) {
            request.getSession().setAttribute(sessionAttribute.getKey(), sessionAttribute.getValue());
        }
    }

    /**
     * Gets parameter value by key
     *
     * @param key is parameter's key
     * @return value
     */
    public String getRequestParameterValue(String key) {
        if (requestParameters.isEmpty()) {
            return "";
        }
        return requestParameters.containsKey(key) ? requestParameters.get(key)[0] : "";
    }

    /**
     * Sets session attribute with key and value to HashMap
     *
     * @param key   is session attribute's key
     * @param value is session attribute's value
     */
    public void setSessionAttributeValue(String key, Object value) {
        sessionAttributes.put(key, value);
    }

    /**
     * Gets session attribute by key
     *
     * @param key is session attribute's key
     * @return value
     */
    public Object getSessionAttributeValue(String key) {
        return sessionAttributes.get(key);
    }

    /**
     * Sets request attribute with key and value to HashMap
     *
     * @param key   is request attribute's key
     * @param value is request attribute's value
     */
    public void setRequestAttributeValue(String key, Object value) {
        requestAttributes.put(key, value);
    }
}
