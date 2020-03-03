package main.java.ru.java_mentor.karimov.servlets;

import main.java.ru.java_mentor.karimov.model.User;
import main.java.ru.java_mentor.karimov.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/login")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user;
        if(login.equals("user") && password.equals("user")){
            user = new User();
            user.setRole("user");
            HttpSession session = request.getSession();
            session.setAttribute("role", user.getRole());
            response.sendRedirect("user");
        }else if(login.equals("admin") && password.equals("admin")){
            user = new User();
            user.setRole("admin");
            HttpSession session = request.getSession();
            session.setAttribute("role", user.getRole());
            response.sendRedirect("admin/list");
        }
    }
}