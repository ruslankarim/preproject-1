package main.java.ru.java_mentor.karimov.servlets;

import main.java.ru.java_mentor.karimov.model.User;
import main.java.ru.java_mentor.karimov.services.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/update")
public class UpdateUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        Long id = Long.valueOf(request.getParameter("id"));
        User user = new User(id, name, address);
        try {
            new UserService().updateUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("list");
    }
}
