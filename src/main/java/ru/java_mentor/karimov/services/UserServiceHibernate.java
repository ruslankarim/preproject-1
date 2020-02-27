package main.java.ru.java_mentor.karimov.services;

import main.java.ru.java_mentor.karimov.dao.UserHibernateDAO;
import main.java.ru.java_mentor.karimov.model.User;

import java.util.List;

public class UserServiceHibernate {
    private UserHibernateDAO userHibernateDAO = new UserHibernateDAO();
    public List<User> getAllUsers() {
        return userHibernateDAO.getAllUsers();
    }

    public void insertUser(User user) {
        userHibernateDAO.insertUser(user);
    }

    public void updateUser(User user){
        userHibernateDAO.updateUser(user);
    }

    public User getUserById(Long id) {
        return userHibernateDAO.getUserByID(id);
    }

    public void deleteUser(Long id) {
        userHibernateDAO.deleteUser(id);
    }
}
