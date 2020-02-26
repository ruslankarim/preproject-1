package main.java.ru.java_mentor.karimov.dao;

import main.java.ru.java_mentor.karimov.executors.Executor;
import main.java.ru.java_mentor.karimov.executors.PreparedExecutor;
import main.java.ru.java_mentor.karimov.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final String SELECT_ALL_USERS = "select * from user";
    private static final String SELECT_USER_BY_ID = "select * from user where id=%s";
    private static final String INSERT_USER = "insert into user (name, address) values (?, ?)";
    private static final String UPDATE_USER = "update user set name = ?, address = ? WHERE id = ?";
    private static final String DELETE_USER = "delete from user where id = ?";

    private Connection connection;
    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        Executor executor = new Executor(connection);
        return executor.execQuery(SELECT_ALL_USERS, result -> {
            while (!result.isLast()){
                result.next();
                Long id = result.getLong(1);
                String name = result.getString(2);
                String address = result.getString(3);
                users.add(new User(id, name, address));
            }
            return users;
        });
    }

    public void insertUser(User user) throws SQLException {
        PreparedExecutor preparedExecutor = new PreparedExecutor(connection);
        preparedExecutor.execInsert(INSERT_USER, statement -> {
            statement.setString(1, user.getName());
            statement.setString(2, user.getAddress());
            statement.execute();
        });
    }

    public void updateUser(User user) throws SQLException {
        PreparedExecutor preparedExecutor = new PreparedExecutor(connection);
        preparedExecutor.execInsert(UPDATE_USER, statement -> {
            statement.setString(1, user.getName());
            statement.setString(2, user.getAddress());
            statement.setLong(3, user.getId());
            statement.execute();
        });
    }

    public User getUserByID(Long id) throws SQLException {
        Executor executor = new Executor(connection);
        return executor.execQuery(String.format(SELECT_USER_BY_ID, id), result -> {
            result.next();
            return new User(id, result.getString(2), result.getString(3));
        });
    }

    public void deleteUser(Long id) throws SQLException {
        PreparedExecutor preparedExecutor = new PreparedExecutor(connection);
        preparedExecutor.execInsert(DELETE_USER, statement -> {
            statement.setLong(1, id);
            statement.execute();
        });
    }

}
