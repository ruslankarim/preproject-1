package main.java.ru.java_mentor.karimov.service;

import main.java.ru.java_mentor.karimov.dao.UserDAO;
import main.java.ru.java_mentor.karimov.dao.UserDaoFactory;
import main.java.ru.java_mentor.karimov.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService instance;

    private UserService() {}

    public static UserService getInstance(){
        if(instance == null){
            instance = new UserService();
        }
        return instance;
    }

    private UserDAO userDAO = new UserDaoFactory().createDao();

    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    public void insertUser(User user) {
        userDAO.insertUser(user);
    }

    public void updateUser(User user){
        userDAO.updateUser(user);
    }

    public User getUserById(Long id) throws SQLException {
        return userDAO.getUserByID(id);
    }

    public void deleteUser(Long id) throws SQLException {
        userDAO.deleteUser(id);
    }
}