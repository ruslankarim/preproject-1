package main.java.ru.java_mentor.karimov.service;

import main.java.ru.java_mentor.karimov.dao.UserHibernateDAO;
import main.java.ru.java_mentor.karimov.model.User;

import java.util.List;

public class UserService {

    private UserHibernateDAO userHibernateDAO = UserHibernateDAO.getInstance();

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
