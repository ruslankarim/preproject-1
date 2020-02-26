package main.java.ru.java_mentor.karimov.executors;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static main.java.ru.java_mentor.karimov.connectionDB.ConnectionDB.getConnection;

public class PreparedExecutor extends Executor {
    public PreparedExecutor(Connection connection) {
        super(connection);
    }

    public void execInsert(String insert, ExecuteHandler prepare) throws SQLException {
        try{
            PreparedStatement preparedStatement = getConnection().prepareStatement(insert);
            prepare.accept(preparedStatement);
            preparedStatement.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FunctionalInterface
    public interface ExecuteHandler {
        void accept(PreparedStatement statement) throws SQLException;
    }
}
