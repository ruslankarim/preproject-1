package main.java.ru.java_mentor.karimov.service;

import main.java.ru.java_mentor.karimov.dao.UserDAO;
import main.java.ru.java_mentor.karimov.dao.UserDaoFactory;
import main.java.ru.java_mentor.karimov.dao.UserHibernateDAO;
import main.java.ru.java_mentor.karimov.dao.UserJdbcDAO;
import main.java.ru.java_mentor.karimov.model.User;
import main.java.ru.java_mentor.karimov.utils.DBHelper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class UserService {

    private static UserService instance;
    private String db = null;
    private Properties property = new Properties();


    private UserService() {}

    public static UserService getInstance(){
        if(instance == null){
            instance = new UserService();
        }
        return instance;
    }

    private UserDAO getDao(){
        UserDaoFactory userDaoFactory = new UserDaoFactory();
        try{
            Properties prop = new Properties();
            String propFileName = "config.properties";
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            property.load(inputStream);
            db = property.getProperty("db");
        }catch (IOException e){
            System.out.println("IOException");
        }
        if(db.equals("hibernate")) {
            return userDaoFactory.getHibernateDao();
        }else{
            return userDaoFactory.getJdbcDao();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        return getDao().getAllUsers();
    }

    public void insertUser(User user) {
        getDao().insertUser(user);
    }

    public void updateUser(User user){
        getDao().updateUser(user);
    }

    public User getUserById(Long id) throws SQLException {
        return getDao().getUserByID(id);
    }

    public void deleteUser(Long id) throws SQLException {
        getDao().deleteUser(id);
    }
}