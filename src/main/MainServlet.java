package main;

import main.model.User;
import main.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(value = "/")
public class MainServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = null;
        try {
           users = new UserService().getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");
        dispatcher.forward(request, response);
    }
}