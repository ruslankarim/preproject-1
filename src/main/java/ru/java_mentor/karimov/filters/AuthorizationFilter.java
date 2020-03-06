package main.java.ru.java_mentor.karimov.filters;

import main.java.ru.java_mentor.karimov.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/login")
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig){}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;
        String login = httpReq.getParameter("login");
        String password = httpReq.getParameter("password");
        if(login != null && password != null){
            if(login.equals("user") && password.equals("user") ||
                    login.equals("admin") && password.equals("admin")){
                chain.doFilter(request, response);
            }else {
                httpResp.sendRedirect(httpReq.getContextPath() + "/usernotexist");
            }
        }else {
            httpResp.sendRedirect(httpReq.getContextPath());
        }


    }

    @Override
    public void destroy(){}
}