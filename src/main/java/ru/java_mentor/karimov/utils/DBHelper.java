package main.java.ru.java_mentor.karimov.utils;

import main.java.ru.java_mentor.karimov.model.User;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {
    private String driverDB = null;
    private String urlDB = null;
    private String userName = null;
    private String password = null;
    private String dialectSQL = null;
    private Properties property = new Properties();

    private static DBHelper instance;
    private DBHelper() {}
    public static DBHelper getInstance(){
        if(instance == null){
            instance = new DBHelper();
        }
        return instance;
    }

    public Connection getConnection(){
        setProperties();
        try{
            Driver driver = (Driver) Class.forName(driverDB).getConstructor().newInstance();
            DriverManager.registerDriver(driver);
            String url = String.format("%s&user=%s&password=%s", urlDB, userName, password);
            return DriverManager.getConnection(url);
        }catch (SQLException |
                InstantiationException |
                InvocationTargetException |
                NoSuchMethodException |
                IllegalAccessException |
                ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    private Configuration getConfiguration(){
        setProperties();
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", dialectSQL);
        configuration.setProperty("hibernate.connection.driver_class", driverDB);
        configuration.setProperty("hibernate.connection.url", urlDB);
        configuration.setProperty("hibernate.connection.username", userName);
        configuration.setProperty("hibernate.connection.password", password);
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        return configuration;
    }

    public SessionFactory getSessionFactory(){
        Configuration configuration = getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private void setProperties(){
        try{
            String propFileName = "config.properties";
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            property.load(inputStream);
            driverDB = property.getProperty("driver");
            urlDB = property.getProperty("url");
            userName = property.getProperty("userName");
            password = property.getProperty("password");
            dialectSQL = property.getProperty("dialectSQL");
        }catch (IOException e){

        }
    }
}
