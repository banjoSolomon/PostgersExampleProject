package org.banjoSolomon.repository;

import org.banjoSolomon.models.User;

import java.sql.Connection;
import java.sql.DriverAction;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserRepository {
    public static Connection connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/banjo-solomon"; //TODO: postgres -> jdbc: postgresql://localhost:5432
        // TODO: mysql-> jdbc
        String username = "postgres";
        String password = "Solomon11";
        try {
            return  DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public User saveUser(User user) {
        return null;

    }

}
