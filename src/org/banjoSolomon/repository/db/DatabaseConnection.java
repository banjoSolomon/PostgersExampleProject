package org.banjoSolomon.repository.db;

import java.sql.*;


public class DatabaseConnection {


    private Connection connection;
    private DatabaseConnection(){}

    private static final class SINGLETON{
        private static final DatabaseConnection databaseConnectionManager =
                new DatabaseConnection();
    }


    public static DatabaseConnection getInstance(){
        return SINGLETON.databaseConnectionManager;
    }

    public Long generateId(String tableName) {
        try(Connection connection = DatabaseConnection.getInstance().getConnection()){
            String sql = "SELECT max(id) FROM "+tableName;
            var statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Long lastIdGenerated = resultSet.getLong(1);
            return lastIdGenerated + 1;
        }catch (SQLException exception){
            throw new RuntimeException(exception.getMessage());
        }
    }


    public Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5432/banjo-solomon"; //TODO: postgres -> jdbc: postgresql://localhost:5432
        // TODO: mysql-> jdbc
        String username = "postgres";
        String password = "Solomon11";
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}