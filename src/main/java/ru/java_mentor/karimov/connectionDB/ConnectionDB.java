package main.java.ru.java_mentor.karimov.connectionDB;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    public static final String DRIVER_MY_SQL = "com.mysql.cj.jdbc.Driver";
    public static final String URLDB = "jdbc:mysql://localhost:3306/usersDB";
    public static final String TIME_ZONE = "UTC";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "re,brhe,br";

    public static Connection getConnection(){
        try{
            Driver driver = (Driver) Class.forName(DRIVER_MY_SQL).getConstructor().newInstance();
            DriverManager.registerDriver(driver);
            String url = String.format("%s?serverTimezone=%s&user=%s&password=%s", URLDB, TIME_ZONE, USER_NAME, PASSWORD);
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
}
