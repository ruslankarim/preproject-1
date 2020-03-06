package main.java.ru.java_mentor.karimov.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*"})
public class RolesFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig){}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;
        HttpSession session = httpReq.getSession();
        if(session.getAttribute("role") != null){
            if(session.getAttribute("role").equals("admin")){
                chain.doFilter(request, response);
            }else if(session.getAttribute("role").equals("user")){
                httpResp.sendRedirect(httpReq.getContextPath() + "/user");
            }
        }else{
            httpResp.sendRedirect(httpReq.getContextPath() + "/usernotexist");
        }
    }

    @Override
    public void destroy() {}
}