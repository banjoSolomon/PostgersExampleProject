package org.banjoSolomon.repository;

import org.banjoSolomon.exception.UserDeleteFailedException;
import org.banjoSolomon.exception.UserUpdateFailedException;
import org.banjoSolomon.models.User;

import java.sql.*;
import java.util.Optional;

@SuppressWarnings(value = {"all"})
public class UserRepository {
    public static Connection connect() throws SQLException {
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

    public User saveUser(User user) {
        String getIdSqlStatement = "select count(*) from users";
        String sql = "insert into users(id, wallet_id) values (?,?)";

        try (Connection connection = connect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(getIdSqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            long currentId = resultSet.getLong(1);

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, currentId + 1);
            if (user.getWalletId() != null)
                preparedStatement.setLong(2, user.getWalletId());
            preparedStatement.execute();

            return getUserBy(currentId + 1);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("Failed to connect to database");
        }
    }


    public User getUserBy(Long id) {
        String sql = "select * from users where id=?";
        try (Connection connection = connect()) {
            var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Long userId = resultSet.getLong(1);
            Long walletId = resultSet.getLong(2);
            User user = new User();
            user.setId(userId);
            user.setWalletId(walletId);
            return user;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
        return null;
    }
    public User updateUser(Long userId, Long walletId){
      try(Connection connection = connect()){
          String sql = "UPDATE users SET wallet_id =? WHERE id=?";
          PreparedStatement statement =   connection.prepareStatement(sql);
          statement.setLong(1, walletId);
          statement.setLong(2, userId);
          statement.executeUpdate();
          return getUserBy(userId);
      }catch (SQLException e){
          throw new UserUpdateFailedException(e.getMessage());

      }
    }

    public void deleteUser(Long userId){
        try (Connection connection = connect()) {
            String sql = "DELETE FROM users WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new UserDeleteFailedException(e.getMessage());
        }
    }

    public Optional findById(Long id) {
        User user = getUserBy(id);
        if (user != null) {
            return Optional.of(user);

        }
        return Optional.empty();
    }



}
