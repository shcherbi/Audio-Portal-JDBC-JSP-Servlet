package by.scherbakov.audioportal.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class SessionRequestContent {
    private Map<String, Object> requestAttributes;
    private Map<String, String[]> requestParameters;
    private Map<String, Object> sessionAttributes;

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

    public void insertValues(HttpServletRequest request) {
        for (Map.Entry<String, Object> requestAttribute : requestAttributes.entrySet()) {
            request.setAttribute(requestAttribute.getKey(), requestAttribute.getValue());
        }
        for (Map.Entry<String, Object> sessionAttribute : sessionAttributes.entrySet()) {
            request.getSession().setAttribute(sessionAttribute.getKey(), sessionAttribute.getValue());
        }
    }

    public String getReguestParameterValue(String key) {
        if (requestParameters.isEmpty()) {
            return "";
        }
        return requestParameters.containsKey(key) ? requestParameters.get(key)[0] : "";
    }
    public void setSessionAttributeValue(String key, Object value) {
        sessionAttributes.put(key, value);
    }
    public Object getSessionAttributeValue(String key) {
        return sessionAttributes.get(key);
    }
    public void setRequestAttributeValue(String key, Object value) {
        requestAttributes.put(key, value);
    }
    public void getRequestAttributeValue(String key) {
        requestAttributes.get(key);
    }
    public Map<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    public void setRequestAttributes(Map<String, Object> requestAttributes) {
        this.requestAttributes = requestAttributes;
    }

    public Map<String, String[]> getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(Map<String, String[]> requestParameters) {
        this.requestParameters = requestParameters;
    }

    public Map<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }

    public void setSessionAttributes(Map<String, Object> sessionAttributes) {
        this.sessionAttributes = sessionAttributes;
    }
}
