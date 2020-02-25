package main.service;

import main.connectionDB.*;
import main.dao.*;
import main.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    Connection connection = ConnectionDB.getConnection();
    UserDAO userDao = new UserDAO(connection);

    public List<User> getAllUsers() throws SQLException {
        return userDao.getAllUsers();
    }

    public void insertUser(User user) throws SQLException {
        userDao.insertUser(user);
    }

    public void updateUser(User user) throws SQLException {
        userDao.updateUser(user);
    }

    public User getUserById(Long id) throws SQLException {
        return userDao.getUserByID(id);
    }

    public void deleteUser(Long id) throws SQLException {
        userDao.deleteUser(id);
    }
}
