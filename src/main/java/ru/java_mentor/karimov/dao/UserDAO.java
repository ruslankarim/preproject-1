package main.java.ru.java_mentor.karimov.dao;

import main.java.ru.java_mentor.karimov.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> getAllUsers() throws SQLException;
    void insertUser(User user);
    void updateUser(User user);
    User getUserByID(Long id) throws SQLException;
    void deleteUser(Long id) throws SQLException;
}
