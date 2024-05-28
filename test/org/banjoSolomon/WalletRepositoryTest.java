package org.banjoSolomon;

import org.banjoSolomon.models.Wallet;
import org.banjoSolomon.repository.WalletRepository;
import org.banjoSolomon.repository.db.DatabaseConnection;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WalletRepositoryTest {
    private final WalletRepository walletRepository = new WalletRepository();

    @Test
    void testSave() {
        Wallet wallet = new Wallet();
        wallet.setBalance(new BigDecimal(1000));
        wallet = walletRepository.save(wallet);
        assertNotNull(wallet);
        assertNotNull(wallet.getId());
    }

    @Test
    public void testFindById(){
        Connection connection = DatabaseConnection.getInstance().getConnection();
        Optional<Wallet> foundWallet = walletRepository.findById(connection,1L);
        assertTrue(foundWallet.isPresent());
    }

}


