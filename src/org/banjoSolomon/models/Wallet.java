package org.banjoSolomon.models;

import java.math.BigDecimal;

public class Wallet {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    private Long id;
    private BigDecimal balance;
}
