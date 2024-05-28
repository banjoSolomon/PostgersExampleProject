package org.banjoSolomon.services;

import org.banjoSolomon.exception.UserNotFoundException;
import org.banjoSolomon.models.User;
import org.banjoSolomon.models.Wallet;
import org.banjoSolomon.repository.UserRepository;
import org.banjoSolomon.repository.db.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class UserService {
    private final UserRepository userRepository = new UserRepository();
    private final WalletService walletService = new WalletService();
    public String transferFunds(Long senderId, Long recipientId, BigDecimal amount) {
        try(Connection connection = DatabaseConnection.getInstance().getConnection()) {
            connection.setAutoCommit(false);
            User sender = userRepository.findById(connection, senderId).orElseThrow();
            User recipient = userRepository.findById(connection, recipientId).orElseThrow();

            Wallet senderWallet = walletService.getWalletBy(connection, sender.getWalletId());
            Wallet recipientWallet = walletService.getWalletBy(connection, recipient.getWalletId());
            senderWallet.setBalance(senderWallet.getBalance().subtract(amount));
            recipientWallet.setBalance(recipientWallet.getBalance().add(amount));
            walletService.update(connection, senderWallet);
            walletService.update(connection, recipientWallet);
            connection.commit();
            return "funds transferred successfully";
        }catch (SQLException exception){
            return null;
        }
    }


    public User getUserById(Connection connection, Long id){
        return userRepository.findById(connection, id)
                .orElseThrow(()->new UserNotFoundException(
                        "user not found"
                ));
    }
}