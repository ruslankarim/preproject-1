package main.java.ru.java_mentor.karimov.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory {
    private String db = null;
    private Properties property = new Properties();

    private UserJdbcDAO getJdbcDao(){
        return UserJdbcDAO.getInstance();
    }

    private UserHibernateDAO getHibernateDao(){
        return UserHibernateDAO.getInstance();
    }

    public UserDAO getDao(){
        UserDaoFactory userDaoFactory = new UserDaoFactory();
        try{
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
}
