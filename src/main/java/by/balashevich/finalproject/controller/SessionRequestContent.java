package by.balashevich.finalproject.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class SessionRequestContent {
    private Map<String, Object> requestAttributes;
    private Map<String, String[]> requestParameters;
    private ServletContext servletContext;

    public SessionRequestContent(HttpServletRequest request) {
        this.requestAttributes = new HashMap<>();
        this.requestParameters = request.getParameterMap();
        this.servletContext = request.getServletContext();
    }

    public void setAttribute(String name, Object object){
        requestAttributes.put(name, object);
    }

    public Object getAttribute(String name){
        return requestAttributes.get(name);
    }

    public void setAttributes(Map<String, Object> attributes){
        requestAttributes = attributes;
    }

    public Map<String, Object> getAttributes(){
        return requestAttributes;
    }

    public void setParameter(String name, String[] value){
        requestParameters.put(name, value);
    }

    public String getParameter(String name){
        StringBuilder sb = new StringBuilder();

        for (String string : requestParameters.get(name)){
            sb.append(string);
        }

        return sb.toString();
    }

    public Map<String, String[]> getParameters(){
        return requestParameters;
    }

    public void setParameters(Map<String, String[]> parameters){
        requestParameters = parameters;
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public void restoreRequest(HttpServletRequest request){
        for(Map.Entry<String, Object> pair : requestAttributes.entrySet()){ //todo is it correct restore method
            request.setAttribute(pair.getKey(), pair.getValue());
        }
    }
}
