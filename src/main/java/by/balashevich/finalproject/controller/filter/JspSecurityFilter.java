package by.balashevich.finalproject.controller.filter;

import by.balashevich.finalproject.controller.command.PageName;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/jsp/*")
public class JspSecurityFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        response.sendRedirect(request.getContextPath() + PageName.INDEX.getPath());
    }

    public void init(FilterConfig config) {
    }
}
