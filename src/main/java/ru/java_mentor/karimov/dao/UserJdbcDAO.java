package main.java.ru.java_mentor.karimov.dao;

import main.java.ru.java_mentor.karimov.utils.ConnectionJDBC;
import main.java.ru.java_mentor.karimov.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {
    private static final String SELECT_ALL_USERS = "select * from user";
    private static final String SELECT_USER_BY_ID = "select * from user where id=%s";
    private static final String INSERT_USER = "insert into user (name, address) values (?, ?)";
    private static final String UPDATE_USER = "update user set name = ?, address = ? where id = ?";
    private static final String DELETE_USER = "delete from user where id = ?";

    private Connection connection = ConnectionJDBC.getConnection();

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            statement.execute(SELECT_ALL_USERS);
            ResultSet resultSet = statement.getResultSet();
            while (!resultSet.isLast()){
                resultSet.next();
                Long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                users.add(new User(id, name, address));
            }
            return users;
        }
    }

    @Override
    public void insertUser(User user){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getAddress());
            preparedStatement.execute();
            preparedStatement.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getAddress());
            preparedStatement.setLong(3, user.getId());
            preparedStatement.execute();
            preparedStatement.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByID(Long id) throws SQLException {
        try(Statement statement = connection.createStatement()) {
            statement.execute(String.format(SELECT_USER_BY_ID, id));
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            return new User(id, resultSet.getString(2), resultSet.getString(3));
        }
    }

    @Override
    public void deleteUser(Long id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
        preparedStatement.setLong(1, id);
        preparedStatement.execute();
        preparedStatement.close();
    }
}
