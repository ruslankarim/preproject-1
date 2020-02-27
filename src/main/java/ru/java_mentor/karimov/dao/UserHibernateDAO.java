package main.java.ru.java_mentor.karimov.dao;

import main.java.ru.java_mentor.karimov.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDAO{

    @Override
    public List<User> getAllUsers() throws SQLException {

        return null;
    }

    @Override
    public void insertUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public User getUserByID(Long id) throws SQLException {
        return null;
    }

    @Override
    public void deleteUser(Long id) throws SQLException {

    }
}
