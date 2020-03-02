package main.java.ru.java_mentor.karimov.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory {
    private String db = null;
    private Properties property = new Properties();

    public UserDAO createDao(){
        try{
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
            property.load(inputStream);
            db = property.getProperty("db");
        }catch (IOException e){

        }
        if(db.equals("hibernate")) {
            return UserHibernateDAO.getInstance();
        }else if(db.equals("jdbc")){
            return UserJdbcDAO.getInstance();
        }else {
            throw new IllegalArgumentException();
        }
    }
}
