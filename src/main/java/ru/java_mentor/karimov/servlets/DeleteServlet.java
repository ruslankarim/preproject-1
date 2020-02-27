package main.java.ru.java_mentor.karimov.servlets;

import main.java.ru.java_mentor.karimov.services.UserServiceHibernate;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/delete")
public class DeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        new UserServiceHibernate().deleteUser(id);
        response.sendRedirect("list");
    }
}
