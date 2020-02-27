package main.java.ru.java_mentor.karimov.services;

import main.java.ru.java_mentor.karimov.dao.*;
import main.java.ru.java_mentor.karimov.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    UserJdbcDAO userJdbcDao = new UserJdbcDAO();

    public List<User> getAllUsers() throws SQLException {
        return userJdbcDao.getAllUsers();
    }

    public void insertUser(User user) throws SQLException {
        userJdbcDao.insertUser(user);
    }

    public void updateUser(User user) throws SQLException {
        userJdbcDao.updateUser(user);
    }

    public User getUserById(Long id) throws SQLException {
        return userJdbcDao.getUserByID(id);
    }

    public void deleteUser(Long id) throws SQLException {
        userJdbcDao.deleteUser(id);
    }
}
